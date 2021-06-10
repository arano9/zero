**项目背景：**该项目是在开发中使用第三方sdk进行交互的场景下催生而来，由于使用sdk交互的时候，经常需要设置很多sdk的参数，以及一些调用的模板逻辑。基于对spring-bean的理解，使用动态代理将这部分逻辑封装起来，sdk的交互逻辑就可屏蔽起来。因为该项目算是本人对spring理解进阶的第一步，特名为zero。

**项目结构:**

打开zero-compose 使用idea 打开setting文件即可导入，所示如图：

![image-20210610124211139](https://raw.githubusercontent.com/arano9/pic-host/main/img/image-20210610124211139.png)



 compose作为根项目编排

 bom作为通用依赖管理

calller-client-sdk模拟sdk调用逻辑

service-stub服务抽象的接口定义，service为具体实现，实际上这里的service可以是任何东西，比如文件的上传下载，比如外部服务的提供调用

sample 是对starter的使用样例

core-stater即核心部分，主要是对bean definition的植入，以及对象生成逻辑的编写，以及与sdk交互逻辑的封装



核心思想是利用bean factory post processor 这个后置周期切入点 ，ConfigurationClassPostProcessor中对ImportBeanDefinitionRegistrar的处理，

利用自定义注解定义扫包路径，生成Definition 注入进去。在定义Definition的使用使用FactoryBean作为BeanClass 从而利用FactoryBean去创建自定义对象。

在创建对象的时候利用动态代理为接口生成对象，且利用对接口的自定义注解作为sdk交互的配置元数据，进行与sdk的交互。



**注：**

- 上面逻辑中有有的service-stub 于sample中的继承的接口关系并不是一定如此，只是由于这边的模拟逻辑刚好也是用java编写。实际上外部服务不一样有这个service-stub，这块的抽象完全在sdk的调用端定义（即sample的位置定义）。

- 代码写的较为简陋，尤其是Handler部分，由于没有明确的sdk交互流程，因此没较好的抽象，算是对这块学习的一个自我总结吧。

  

测试逻辑直接跑sample中的app即可。