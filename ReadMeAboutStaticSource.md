# [Springboot 学习demo]https://github.com/zconggithub/zcgithub.git
create by 周聪 	QQ：2632652610	

#Spring Boot的静态资源

Spring Boot中静态资源（JS, 图片）等应该放在什么位置?
Spring Boot的静态资源，比如图片应该放在什么位置呢， 如果你放在传统WEB共的类似地方， 比如webapp或者WEB-INF下，你会得到一张示意文件未找到的破碎图片。

1. 默认位置：

Spring Boot能大大简化WEB应用开发的原因， 最重要的就是遵循“约定优于配置”这一基本原则。Spring Boot的关于静态资源的默认配置已经完全满足绝大部分WEB应用的需求。没必要去弄手续繁杂的自定义，用Spring Boot的约定就好了。

在Maven 工程目录下，所有静态资源都放在src/main/resource目录下，结构如下：
src/main/resource
          |__________static
                        |_________js
                        |_________images
                        |_________css
                 .....
                 
 例如，imges目录下的My_icon@2x.png 在HTML/JSP中访问是的路径就是<img src="/images/5.png">, 因为Spring Boot的缺省工作目录就是src/main/java, 当访问资源时，就是src/main/resources, 而/static/**被SPRING BOOT被映射到了classpath:/static下。所以也可以不带起始的“/”，直接写成<img src="images/5.png">。                