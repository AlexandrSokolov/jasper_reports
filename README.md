### Note:

You cannot build the whole project, it fails cause of:
```qute
 Execution repackage of goal org.springframework.boot:spring-boot-maven-plugin:3.1.1:repackage failed: Unable to find main class
```

The project is created as a demo project to see JR Templates in the JR Studio 
and run tests manually to open the generated reports immediately.

### Documentation and tutorials:

* [The JasperReports Ultimate Guide Third Edition](https://jasperreports.sourceforge.net/JasperReports-Ultimate-Guide-3.pdf)
* [The JasperReports Ultimate Guide Third Edition (local version)](docs/JasperReports-Ultimate-Guide-3.pdf)
* [TIBCO Jaspersoft Studio User Guide](https://community.jaspersoft.com/documentation/v600-v601/tibco-jaspersoft-studio-user-guide)
* [JasperReports Tutorial](https://www.tutorialspoint.com/jasper_reports/index.htm)

When you start working with Jasper Reports you must understand:
* [Report Template Sections](docs/Report_Template_Sections.md)
* [Report Elements](docs/Report_Elements.md)
* [Data passing into the report](docs/Data_Passing.md)
* [Table Element](docs/Table.element.md)
* [Custom fonts](docs/Custom_font.md)
* JR Report Generation Workflow. See [ReportAbstractBaseTest#generateReport](src/test/java/com/example/jr/ReportAbstractBaseTest.java)

### Download Jaspersoft Studio

* [Find the required version](https://sourceforge.net/projects/jasperstudio/files/)
* Download file for your plaform. `js-studiocomm_x.x.x_linux_x86_64.tgz` for Ubuntu
* Extract and run `Jaspersoft Studio` binary file.