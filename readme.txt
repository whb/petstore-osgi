����������������ʾDWR��WebX2.0���ɵ����ù��̡�������
2����petstore�޹ص�DWR��Ӧ��:
  petstore/dwrdemo/dynamictext.html
  petstore/dwrdemo/javachat.html
1����petstore���ɣ���ʾpetstore Catalog�����DWRӦ�ã�û�м��ɽ��棬ͨ������url����:
  petstore/dwr/test/CatalogFacade

Դ������֯:
	datasource  :����Դ����bundle
	dao         :model��dao��bundle
	service     :����ӿں�VO��bundle
	service.impl:����ʵ�ֵ�bundle
	web         :web���bundle
  lib         :���Ƶ�DWR3.0rc1��bundle��һ������ʱ��bundle���òο��ļ�dwr.launch

����˵��:
  1 ȷ��WebX2.0����������ȷ��
  2 ����lib\webx2.dwr_3.0.0.116.rc1.jar��eclipse��pluginsĿ¼�£�
  3 ��eclipse�������й��̣�
  4 �޸�petstore-datasource�����е�spring-petstore-datasource.xml�뱾�����ݿ⻷��һ�£�
  5 ����lib\dwr.launch�����̶�Ӧ��workspaces�µ�.metadata\.plugins\org.eclipse.debug.core\.launchesĿ¼�£����߲ο�petstore2.0���������ã�������dwr-3.0.0.116.rc1��bundle�� 
  6 ����eclipse��Run->Open Run Dialog->dwr���á�

ʹ��˵��:
  petstore-web������:
    web/dwrdemo/dynamictext.html: �򵥵Ķ�̬�ı������ӣ�
    web/dwrdemo/javachat.html: �򻯰�������ҵ����ӣ�ʹ��Reverse Ajax������
    META-INF/spring/dwr-demo.xml: ����2�����ӵ�DWR��Spring���ɵ������ļ���
    META-INF/spring/init-web.xml: ʹ��DWRʱ����ʼ��web��Spring�����ļ���
    META-INF/spring/osgi-petstore-service.xml: ��petstore���ɵ�DWR��Spring�����ļ���
    com.dwrdemo.reverseajax.chat.*: ���������Ӷ�Ӧ��java�ļ���
    com.dwrdemo.simpletext.*: ��̬�ı����Ӷ�Ӧ��java�ļ���
    mypetstore.web.InitWebDWRSpringServlet: DWR�汾��ע��Web��������;
    mypetstore.web.dwr.facade.*: DWR��petstore��ʹ�ã����struts action���ࡣ
