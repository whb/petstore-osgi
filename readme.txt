本样例代码用于演示DWR与WebX2.0集成的配置过程。包括：
2个与petstore无关的DWR简单应用:
  petstore/dwrdemo/dynamictext.html
  petstore/dwrdemo/javachat.html
1个与petstore集成，演示petstore Catalog服务的DWR应用，没有集成界面，通过如下url访问:
  petstore/dwr/test/CatalogFacade

源代码组织:
	datasource  :数据源配置bundle
	dao         :model和dao的bundle
	service     :服务接口和VO的bundle
	service.impl:服务实现的bundle
	web         :web层的bundle
  lib         :自制的DWR3.0rc1的bundle和一份运行时的bundle配置参考文件dwr.launch

运行说明:
  1 确认WebX2.0开发环境正确；
  2 复制lib\webx2.dwr_3.0.0.116.rc1.jar到eclipse的plugins目录下；
  3 在eclipse导入已有工程；
  4 修改petstore-datasource工程中的spring-petstore-datasource.xml与本地数据库环境一致；
  5 复制lib\dwr.launch到工程对应的workspaces下的.metadata\.plugins\org.eclipse.debug.core\.launches目录下；或者参考petstore2.0的运行配置，再增加dwr-3.0.0.116.rc1的bundle； 
  6 运行eclipse中Run->Open Run Dialog->dwr配置。

使用说明:
  petstore-web工程中:
    web/dwrdemo/dynamictext.html: 简单的动态文本的例子；
    web/dwrdemo/javachat.html: 简化版的聊天室的例子，使用Reverse Ajax技术；
    META-INF/spring/dwr-demo.xml: 上述2个例子的DWR与Spring集成的配置文件；
    META-INF/spring/init-web.xml: 使用DWR时，初始化web的Spring配置文件；
    META-INF/spring/osgi-petstore-service.xml: 与petstore集成的DWR与Spring配置文件；
    com.dwrdemo.reverseajax.chat.*: 聊天室例子对应的java文件；
    com.dwrdemo.simpletext.*: 动态文本例子对应的java文件；
    mypetstore.web.InitWebDWRSpringServlet: DWR版本的注册Web环境的类;
    mypetstore.web.dwr.facade.*: DWR在petstore中使用，替代struts action的类。
