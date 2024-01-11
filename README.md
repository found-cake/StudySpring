## 사용된 Lombok 어노테이션
| 이름                      | 위치            | 설명                                                          |
|-------------------------|---------------|-------------------------------------------------------------|
| RequiredArgsConstructor | class         | 기본 값이 없는 final이나 NonNull이라는 어노테이션이 없는 프로버티에 대한 생성자를 자동으로 생성 |
| Getter                  | class or 프로버티 | Getter 메소드들을 자동으로 생성                                        |
| Builder                 | class         | Builder 패턴을 구현                                              |
| NoArgsConstructor       | class         | 파라미터가 없는 생성자를 생성                                            |

## 사용된 Spring 어노테이션(Security 포함)
| 이름                | 설명                                          |
|-------------------|---------------------------------------------|
| Configuration     | 클래스에 스프링 빈 및 구성 로직이 포함되어 있음을 나타냄            |
| EnableWebSecurity | Spring Security 를 사용하도록 활성화                 |
| Bean              | Bean 객체 등록                                  |
| RestController    | 해당 클래스의 모든 메서드의 반환 값이 HTTP 응답 본문으로 직접 전송    |
| Controller        | Spring MVC에서 웹 요청을 처리하는 데 사용됨               |
| RequestMapping    | 메서드 또는 클래스에 URL 매핑을 정의                      |
| GetMapping        | Get 메서드에 대한 요청을 처리하는 메서드에 지정                |
| PostMapping       | POST 메서드에 대한 요청을 처리하는 메서드에 지정               |
| ModelAttribute    | 요청 파라미터를 객체에 바인딩하기 위해 사용                    |
| PathVariable      | 요청 URI의 일부를 추출하여 메서드 파라미터로 전달하는 데에 사용       |
| RequestParam      | HTTP 요청의 매개변수를 메서드 파라미터로 전달하는 데에 사용         |
| Repository        | 해당 클래스가 응용 프로그램에 대한 데이터 접근을 제공하는 레파지토리라고 표시 |
