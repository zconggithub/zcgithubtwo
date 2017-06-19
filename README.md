# [Springboot 学习demo]https://github.com/zconggithub/zcgithub.git
create by 周聪 	QQ：2632652610	
#《==说明=以下全是indexcontroller（上传图片）的配置说明=》
1、IndexController对应的处理index.html页面模板是使用Thymeleaf，文件上传工具使用Apache的commons-io		==>具体依赖请看pom文件
2、SecondController对应的处理second.html页面模板是使用freemarker，文件上传工具使用Apache的commons-io		==>具体依赖请看pom文件

#（一）配置文件说明
				#配置文件详解（一）#
静态资源路径是指系统可以直接访问的路径，且路径下的所有文件均可被用户直接读取。
在Springboot中默认的静态资源路径有：classpath:/META-INF/resources/，classpath:/resources/，classpath:/static/，classpath:/public/，从这里可以看出这里的静态资源路径都是在classpath中（也就是在项目路径下指定的这几个文件夹）
				#配置文件详解（二）#
#web.upload-path这个属于自定义的属性，指定了一个路径，注意要以/结尾；
spring.mvc.static-path-pattern=/**表示所有的访问都经过静态资源路径；
spring.resources.static-locations在这里配置静态资源路径，前面说了这里的配置是覆盖默认配置，所以需要将默认的也加上否则static、public等这些路径将不能被当作静态资源路径，在这个最末尾的file:${web.upload-path}之所有要加file:是因为指定的是一个具体的硬盘路径，其他的使用classpath指的是系统环境变量

#（二）文件上传注意点：===《经常会遇到以下问题》
SpringBoot做文件上传时出现了The field file exceeds its maximum permitted size of 1048576 bytes.错误，显示文件的大小超出了允许的范围。查看了官方文档，原来spring Boot工程嵌入的tomcat限制了请求的文件大小，这一点在Spring Boot的官方文档中有说明，原文如下

65.5 Handling Multipart File Uploads
Spring Boot embraces the Servlet 3 javax.servlet.http.Part API to support uploading files. By default Spring Boot configures Spring MVC with a maximum file of 1Mb per file and a maximum of 10Mb of file data in a single request. You may override these values, as well as the location to which intermediate data is stored (e.g., to the /tmp directory) and the threshold past which data is flushed to disk by using the properties exposed in the MultipartProperties class. If you want to specify that files be unlimited, for example, set the multipart.maxFileSize property to -1.The multipart support is helpful when you want to receive multipart encoded file data as a @RequestParam-annotated parameter of type MultipartFile in a Spring MVC controller handler method.
文档说明表示，每个文件的配置最大为1Mb，单次请求的文件的总数不能大于10Mb。要更改这个默认值需要在配置文件（如application.properties）中加入两个配置

###需要设置以下两个参数在配置文件中

multipart.maxFileSize
multipart.maxRequestSize

###Spring Boot 1.3.x或者之前
multipart.maxFileSize=100Mb
multipart.maxRequestSize=1000Mb

###Spring Boot 1.4.x或者之后
spring.http.multipart.maxFileSize=100Mb
spring.http.multipart.maxRequestSize=1000Mb


#springboot的tomcat是内置的，每次启动都是一个新的。 能不能尝试把文件存放在磁盘上某个固定的目录上
#【secondController文件是上传文件】

