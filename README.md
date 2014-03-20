### Introduction

Bu proje Robert C. Martin'in (Uncle Bob) [Clean Architecture](http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html) yaklaşımından esinlenilerek geliştirilmiş bir EBI (Entity, Boundary, Interactor) pattern denemesidir.
Bu mimari yaklaşım uygulama iş mantığının presentation, data, dış sistem, framework vs. gibi her türlü katmandan tamamen bağımsız olmasını ve use case odaklı tasarlanmasını önerir. Belirtilen katmanlar uygulamaya bir plugin gibi entegre olur.
UI katmanı her ne olursa olsun (web, desktop, mobile) bir iletim mekanizmasıdır;  database uygulama açısından bir detaydır.
Bahsedilen katmanlardaki bir değişikliğin iş katmanını etkilemesine müsade etmez.
Bu sayede değişime daha müsait, daha temiz ve testable yazılım geliştirmenin olanaklarını bize sunar.

Bu mimari tabiri caizse soğanın katmanlarına benzetilebilir. En dışta alt seviye mekanizmalar vardır.
İçeriye doğru seviye yükselir, pür iş kuralları vardır. Bağımlıklar daima dıştan içe doğru olmalıdır.
Dış katmanlarındaki değişiklikler mümkün olduğunca iç katmanlarda değişikliğe yol açmamalıdır.
Mesela entity veya use case katmanından web katmanındaki bir classa referans edilemez.
Dış dünya ile iletişim request/response adaptorler vasıtasıyle gerçekleşir.

### Modüller

**todo-api**:  
Boundary interfaceleri, request/response modelleri, DTO ve exception classları vs. bulunmaktadır.

**todo-core**:  
Entityler, Interactor (use case) implementationları, repository interfaceleri burada bulunuyor. todo-api modülündeki RequestBoundary interfaceleri Interactor classlarında implement ediliyor. Sadece todo-api modülüne depend etmektedir.

**todo-data-mock**:  
todo-core modülündeki repository interfacelerin in-memory implementationları bu modülde bulunuyor.
todo-data-jpa, todo-data-nosql vs. gibi alternatifleri de oluşturulabilir. 
Repository implementationların todo-core'a transparent bir şekilde bind edilebilmesi için ServiceLoader configurasyonları tanımlıdır. Bu sayede todo-core runtimeda bulduğu herhangi bir todo-data-* modülüyle  çalışabilmektedir.

**todo-bootstrap**:  
Burada dependency injection ile todo-api'deki RequestBoundary interfacelerine todo-core'daki Interactor classları bind edilir, Repository interfacelerine de JDK service provider konfigürasyonuyla tanımlanmış olan todo-data-* modülündeki concrete repositoryler bind edilir. Repositoryler Interactorlara inject edilir.
Dependency injection container olarak Guice kullanılmıştır.

**todo-web**:  
todo-bootstrap'ta wire edilmiş olan RequestBoundary'ler TodoController'a inject edilir. TodoController RequestBoundary interface üzerinden interactorlara istekte bulunur. 
ReponseBoundary interfaceleri TodoController'da implement edildiği için interactordan donen cevapları da karşılamış olmaktadır.todo-api, todo-data-mock ve toro-bootstrap modüllerine depend eder. 
Mvc framework olarak JSF-2 kullanılmıştır. Uygulama konsepti ve önyüz tasarımı [TodoMVC](http://todomvc.com/) projesinden uyarlanmıştır.

Modüller arasında compile-time classpath izolasyonu sağlanmaya çalışıldı. Mesela todo-core modülü concrete repositoryler hangileridir veya response boundaryler nerede implement edilmiştir, çalışacağı client kimdir bilmemektedir. todo-web sadece todo-api ve todo-bootstrap modüllerinyle muhataptır ve interactor classlarını tanımamaktadır. 

### Kısıtlamalar
Java type erasur dolayısıyla 
```java
public class CreateTodoInteractor extends TodoInteractor
        implements RequestBoundary<CreateTodoRequest> {
        ...
}       
```
şeklinde tüm request/response tipleriyle çalışabilecek şekilde generic boundary interface kullanılamadığı için 
```CreateTodoRequestBoundary```, ```CreateTodoResponseBoundary```, ```CompleteTodoRequestBoundary```, ```CompleteTodoResponseBoundary```... 
şeklinde herbir request/response type için ayrı boundary interface yazılması gerekti.


### Running
```
$ mvn clean install && mvn -f todo-web/pom.xml jetty:run
```
http://localhost:8080/todos.jsf


### Sonuç

Clean Architecture yaklaşımı Todo çapında bir uygulama için seremonisi biraz fazla gibi gözükse de daha karmaşık uygulama domainlerinde bu bedeli her açıdan fazlasıyla karşılayacağını düşünüyorum.  
Clean Architecture konusunda henüz official bir örnek yok bildiğim kadarıyla. Robert C. Martin'in sunumlarından insanların anlayabildiklerine göre yaptıkları bazı örnekler var.  
Bu örnekler arasında aklıma yatan Patrick Roza'nın  [CCC.TestApp](https://github.com/sickboy/CCC.TestApp) uygulamasından en çok yararlandım.













