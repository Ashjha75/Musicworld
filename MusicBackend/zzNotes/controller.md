This is controller.md file basic of all the thing present and required in controller 
# Controller

SO, this is the starting point of the request handling in the Spring MVC application. The controller is responsible for processing the request and returning the response. The controller is a simple Java class that is annotated with `@Controller` annotation
`@RestController` is used to create RESTful web services. It is a convenience annotation that combines `@Controller` and `@ResponseBody`.
`@RequestMapping("/api/v1/categories")` is used to map the request to the appropriate controller

`@Autowired` : here we have used `@Autowired` annotation to inject the service layer into the controller. This is a form of `Dependency Injection` in Spring.

`@GetMapping` : This annotation is used to map the HTTP GET request to the appropriate method. It is a composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.GET)`.

`@PostMapping` : This annotation is used to map the HTTP POST request to the appropriate method. It is a composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.POST)`.

`@PutMapping` : This annotation is used to map the HTTP PUT request to the appropriate method. It is a composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.PUT)`.

`@DeleteMapping` : This annotation is used to map the HTTP DELETE request to the appropriate method. It is a composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.DELETE)`.

`@PathVariable` : This annotation is used to extract the values from the URI. It is used to extract the values from the URI and pass it to the method.

`@RequestBody` : This annotation is used to bind the HTTP request body to the method parameter. It is used to extract the request body and pass it to the method.

`@RequestParam` : This annotation is used to extract the values from the query string. It is used to extract the values from the query string and pass it to the method.

`@Valid` : This annotation is used to validate the request body. It is used to validate the request body using the `Hibernate Validator`.

`@ResponseStatus(HttpStatus.CREATED)` : This annotation is used to set the HTTP status code of the response. It is used to set the HTTP status code of the response to `201 Created`.

`@ExceptionHandler` : This annotation is used to handle the exceptions. It is used to handle the exceptions thrown by the controller methods.

`@ControllerAdvice` : This annotation is used to define the global exception handler. It is used to define the global exception handler for the application.

`@RequestParam(name = "page", defaultValue = AppConstants.PAGE_NUMBER, required = false)` : This is used for pagination process 
