// package com.poc.bloodx.config

// import org.springframework.security.core.GrantedAuthority
// import org.springframework.security.core.authority.SimpleGrantedAuthority
// import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal
// import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal
// import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector

// class CustomAuthorityOpaqueTokenIntrospector(private val delegate: OpaqueTokenIntrospector) : OpaqueTokenIntrospector {

//     companion object {
//         const val ZITADEL_ROLES_CLAIM = "urn:zitadel:iam:org:project:roles"
//     }

//     override fun introspect(token: String): OAuth2AuthenticatedPrincipal {
//         val principal = delegate.introspect(token)
//         return DefaultOAuth2AuthenticatedPrincipal(
//             principal.name, principal.attributes, extractAuthorities(principal)
//         )
//     }

//     private fun extractAuthorities(principal: OAuth2AuthenticatedPrincipal): Collection<GrantedAuthority> {
//         val authorities = mutableSetOf<GrantedAuthority>()

//         val claims: Map<String, Any>? = principal.getAttribute(ZITADEL_ROLES_CLAIM)
//         if (claims == null) {
//             return authorities
//         }

//         claims.keys.forEach { role ->
//             // authorities.add(SimpleGrantedAuthority("ROLE_$role"))
//         }

//         return authorities
//     }
// }