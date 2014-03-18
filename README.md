###Introduction

Bu proje Robert C. Martin'in (Uncle Bob) [Clean Architecture](http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html) yaklaşımından esinlenilerek geliştirilmiş bir EBI (Entity, Boundary, Interactor) pattern denemesidir.
Bu mimari yaklaşım uygulama iş mantığının presentation, data, dış sistem, framework vs. gibi her türlü katmandan tamamen bağımsız olmasını ve use case odaklı tasarlanmasını önerir. 
UI katmanı her ne olursa olsun (web, desktop, mobile) bir iletim mekanizmasıdır;  database ise sadece bir detaydır.
Bahsedilen katmanlardaki bir değişikliğin iş katmanını etkilemesine müsade etmez.
Bu sayede değişime daha müsait, daha temiz ve testable yazılım geliştirmenin olanklarını bize sunar.

Bu mimari tabiri caizse soğanın katmanlarına benzetilebilir. En dışta alt seviye mekanizmalar vardır.
İçeriye doğru seviye yükselir, pür iş kuralları vardır. Bağımlıklar daima dışarıdan içe doğru olmalıdır.
Dış katmanlarındaki değişiklikler mümkün olduğunca iç katmanlarda değişikliğe yol açmamalıdır.
Mesela entity veya use katmanından web katmanındaki bir classa referans edilemez.

Entity: 
Uygulamaya ait en temel high level veri yapılarını ve iş kurallarını barındırırlar. 

Boundary:
Uygulamanın dış dünyayla iletişimi bu katmanda gerçekleştirilir. Gelen istekler burada iş mantığının gerektirdiği en uygun veri yapılarına dönüştürülür. Mesela web katmanından gelen request, DTO gibi parametreler burada entitylere dönüştürülür. Aynı şekilde entityler kullanılan database teknolojisine göre JDBC, JPA, NoSql vs. her ne ise veri yapıları arasındaki dönüştürme burada yapılır. Entityler database spesifik hiçbir şey içermez.

Interactor/Use Case:
Use caselerin gerektirdiği tüm iş mantığı entityler kullanılarak bu katmanda gerçekleştirilir. Entityler ve dış dünya arası veri akışı burada olur.



###Modüller




