### Documentation:

[Template Structure](https://community.jaspersoft.com/documentation/tibco-jaspersoft-studio-user-guide/v60/template-structure)
[Report Sections](https://www.tutorialspoint.com/jasper_reports/jasper_report_sections.htm)

### Topics

* [Template Structure/Report Sections](#template-structurereport-sections)
* [Multiple pages that appear only once at the beginning of the report](#multiple-pages-that-appear-only-once-at-the-beginning-of-the-report)

### Template Structure/Report Sections

* Title - This section appears only once at the beginning of the report.
* Page Header - This section appears at the beginning of each page in the generated document.
* Column Header - This section appears at the beginning of each column in the generated document.
* Detail - This section is repeated for each line of data supplied by the report's data source.
* Column Footer - This section appears at the bottom of each column
* Page Footer - This section appears at the bottom of each page.
* Last Page Footer - This section replaces the regular page footer on the last page of the report.
  In case, the summary section is also present, then this might not be the very last page of the document.
  This section is sometimes useful when summary information has to be displayed at the bottom of the last page.
* Summary - This section appears only once at the end of the report.
* No Data - This section is printed when the When No Data Print report property is set to No Data Section.
  If the <noData> section is defined in the report template, and if the data source is empty,
  then the <noData> section will be the only one taken into account at fill time,
  and its content will produce the report output.
* Background - The background section is displayed on every page and cannot overflow to the next page.
  Elements placed on this section are evaluated at page initialization time and are displayed in the background.
  All other page objects are displayed on top of the background objects.
  This section is useful for creating page watermarks.

Remember the following:
* The content of the Summary, Title, Page Header and Page Footer bands, as well as the background, is static.
  Every element that is placed in these bands in a template appears in every report that uses this template.
* In the Column Header band there should be only a Static Text element, and its text content must be Label.
* In the Detail band there should be only a Text Filed with the string “Field” (including the double quotes).
  Again, this is used to generate every field that goes in this band.
* **You can create as many Detail band as needed!**
* Column Header and Column Footer - If the report has only one column defined,
  then column header and footer sections are ignored.

### Multiple pages that appear only once at the beginning of the report

Your report has some pages, that must appear only once at the beginning of the report.

You cannot use the Title Band, because it is limited by the height of the document 
and cannot contain elements for more than a single page.

Page breaks do not help, cause they only break the title onto different pages, 
but you still cannot use the whole area, needed to structure several pages.

Another disadvantage of using Title Band for this purpose - it cannot use Page Header or Page Footer elements. 

You have some options:
* Use subreports. Create subreport per page and refer them in your main report.
* Use Detail Band for the first pages with `printWhenExpression` based on `$V{PAGE_NUMBER}` variable together with 
a Page Break element to start a new Detail Band on the new page. 

For instance to show the 2nd page, structured as Detail Band, configure with:
```xml
<printWhenExpression><![CDATA[$V{PAGE_NUMBER} == 2]]></printWhenExpression>
```

