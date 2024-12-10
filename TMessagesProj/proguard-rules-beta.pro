-keep class org.telegram.tgnet.** { *; }

# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn java.awt.datatransfer.DataFlavor
-dontwarn java.awt.datatransfer.Transferable
-dontwarn javax.security.auth.callback.NameCallback
-dontwarn javax.security.sasl.RealmCallback
-dontwarn javax.security.sasl.RealmChoiceCallback
-dontwarn javax.security.sasl.Sasl
-dontwarn javax.security.sasl.SaslClient
-dontwarn javax.security.sasl.SaslClientFactory
-dontwarn javax.security.sasl.SaslException
-keep class org.tg_member.features.login.LoginFragment.**{*;}
-keep class org.tg_member.features.login.EmailCodeSender.**{*;}