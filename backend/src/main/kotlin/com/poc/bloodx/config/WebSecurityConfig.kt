// package demo.app.config

// import org.springframework.context.annotation.Bean
// import org.springframework.context.annotation.Configuration
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
// import org.springframework.security.config.annotation.web.builders.HttpSecurity
// import org.springframework.security.config.http.SessionCreationPolicy
// import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector
// import org.springframework.security.web.SecurityFilterChain
// import com.poc.bloodx.config.CustomAuthorityOpaqueTokenIntrospector

// @Configuration
// @EnableMethodSecurity
// class WebSecurityConfig(private val introspector: OpaqueTokenIntrospector) {

//     /**
//      * Configures basic security handler per HTTP session.
//      * <p>
//      * <ul>
//      * <li>Stateless session (no session kept server-side)</li>
//      * <li>CORS set up</li>
//      * <li>Require authentication for the /api/tasks paths</li>
//      * <li>access token converted into Spring token</li>
//      * </ul>
//      *
//      * @param http security configuration
//      * @throws Exception any error
//      */
//     @Bean
//     @Throws(Exception::class)
//     fun filterChain(http: HttpSecurity): SecurityFilterChain {
//         http
//             .csrf { it.disable() }
//             .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
//             .authorizeHttpRequests {
//                 it.requestMatchers("/api/tasks").authenticated()
//                 it.anyRequest().permitAll()
//             }
//             .oauth2ResourceServer {
//                 it.opaqueToken { it.introspector(introspector()) }
//             }

//         return http.build()
//     }

//     private fun introspector(): OpaqueTokenIntrospector {
//         return CustomAuthorityOpaqueTokenIntrospector(introspector)
//     }
// }
