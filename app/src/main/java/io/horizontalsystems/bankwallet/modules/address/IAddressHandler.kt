package io.horizontalsystems.bankwallet.modules.address
import io.horizontalsystems.bankwallet.entities.Address
import io.horizontalsystems.ethereumkit.core.AddressValidator
import io.horizontalsystems.marketkit2.models.CoinType
import com.unstoppabledomains.resolution.Resolution
import java.net.URL

interface IAddressHandler {
    fun isSupported(value: String): Boolean
    fun parseAddress(value: String): Address
}

class AddressHandlerUdn(private val coinType: CoinType, private val coinCode: String) : IAddressHandler {
    private val resolution = Resolution()
    private val currencyVersion by lazy { version(coinType, coinCode) }

    override fun isSupported(value: String): Boolean {
        return value.contains(".") && resolution.isSupported(value)
    }

    override fun parseAddress(value: String): Address {
        return if (currencyVersion != ""){
            Address(resolveMultiChain(value, coinCode, currencyVersion), value)
        }else {
            Address(resolveSingleChain(value, coinCode), value)
        }
    }

    private fun resolveMultiChain(domain: String, currency: String, version: String): String {
        val resolvedAddress: String
        try{
            resolvedAddress = resolution.getMultiChainAddress(domain, currency, version)
            return resolvedAddress
        }catch(e: Exception){
            throw Exception("Unstoppable Domains - Resolution Error: $e")
        }
    }

    private fun resolveSingleChain(domain: String, currency: String): String {
        val resolvedAddress: String
        if (currency == "BNB"){
            return resolveMultiChain(domain, currency, "BEP20")
        }
        try{
            resolvedAddress = resolution.getAddress(domain, currency)
            return resolvedAddress
        }catch(e: Exception){
            throw Exception("Unstoppable Domains - Resolution Error: $e")
        }
    }

    companion object {
        private fun version(coinType: CoinType, coinCode: String): String {
            val url = "https://unstoppabledomains.com/api/uns-resolver-keys"
            val expectedVersion = coinType.toString().split("|")[0].uppercase()
            val supportedCurrencies: MutableList<String> = ArrayList()
            var version = ""
            try{
                val jsonString = URL(url).readText()
                val keys = jsonString.split("keys\":{\"crypto.")[1].split("crypto.")
                for (key in keys) {
                    val currency = key.split("\":{\"deprecated")
                    supportedCurrencies.add(currency[0])
                }
            }catch(e: Exception){
                throw Exception("Unstoppable Domains - API Error: $e")
            }
            for (currency in supportedCurrencies) {
                if(currency.startsWith(coinCode)){
                    if(currency.contains(expectedVersion)){
                        version = expectedVersion
                    }
                }
            }
            if (expectedVersion.contains("[0-9]".toRegex()) && version != expectedVersion) {
                throw Exception("Unstoppable Domains - Unsupported Currency Version: $expectedVersion")
            } else {
                return version
            }
        }
    }
}

class AddressHandlerEvm : IAddressHandler {
    override fun isSupported(value: String) = try {
        AddressValidator.validate(value)
        true
    } catch (e: AddressValidator.AddressValidationException) {
        false
    }

    override fun parseAddress(value: String): Address {
        val evmAddress = io.horizontalsystems.ethereumkit.models.Address(value)
        return Address(evmAddress.hex)
    }

}

class AddressHandlerPure : IAddressHandler {
    override fun isSupported(value: String) = true

    override fun parseAddress(value: String) = Address(value)

}