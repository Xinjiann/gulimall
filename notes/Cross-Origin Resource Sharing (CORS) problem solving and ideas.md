# Cross-Origin Resource Sharing (CORS) problem solving and ideas

Accessing the backend data with ip address successed, but failed when frontend do so, as shown below: 


![](https://github.com/Xinjiann/java/blob/main/img/%E8%B7%A8%E5%9F%9F2.png)

Its due to the browser restrictions on javascript's same-origin policy. Senarios below are the cross-domain examples.

![](https://github.com/Xinjiann/java/blob/main/img/%E8%B7%A8%E5%9F%9F.png)

## Analysing

Before the formal communication, a cross-domain request will add an HTTP query request, "preflight" request.

The browser first asks the server whether the domain name of the current webpage is in the server's permission list, and which HTTP verbs and header fields can be used. The browser will send a formal XMLHttpRequest request only if it gets a positive answer, otherwise it will report an error.

A sample "preflight" request:

```
OPTIONS /cors HTTP/1.1
Origin: http://localhost:1000
Access-Control-Request-Method: GET
Access-Control-Request-Headers: X-Custom-Header
User-Agent: Mozilla/5.0...
```

Origin: Indicating which domain the current request belongs to (protocol + domain name + port). The service will decide whether to allow cross-domain based on origin.

Access-Control-Request-Method: The request method that will be used next, such as PUT

Access-Control-Request-Headers: additional header information

When server receives the pre-check request, if it is allowed to cross domains, it will respond:

```
HTTP/1.1 200 OK
Date: Mon, 01 Dec 2008 01:15:39 GMT
Server: Apache/2.0.61 (Unix)
Access-Control-Allow-Origin: http://miaosha.jd.com
Access-Control-Allow-Credentials: true
Access-Control-Allow-Methods: GET, POST, PUT
Access-Control-Allow-Headers: X-Custom-Header
Access-Control-Max-Age: 1728000
Content-Type: text/html; charset=utf-8
Content-Encoding: gzip
Content-Length: 0
Keep-Alive: timeout=2, max=100
Connection: Keep-Alive
Content-Type: text/plain
```

Then we could get the solution

## Soulution 

* The response header of the service needs to carry Access-Control-Allow-Credentials and be true.
* The browser initiates ajax and needs to specify withCredentials as true.
* The Access-Control-Allow-Origin in the response header must not be *, it must be the specified domain name.

It is suffering to add these segements to every responed, we can simply add a filter to gateway.

```java
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {

        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://manager.gmall.com");
        config.addAllowedOrigin("http://www.gmall.com");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(corsConfigurationSource);
    }
}
```
Problem solved.
