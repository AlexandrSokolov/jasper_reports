### Documentation

* [Report Elements](https://community.jaspersoft.com/documentation/tibco-jaspersoft-studio-user-guide/v60/report-elements)
* [JRElement](https://jasperreports.sourceforge.net/api/net/sf/jasperreports/engine/JRElement.html)

Some specific notes on the elements:

* [`Page Number` and `Page X of Y`](#page-number-and-page-x-of-y)
* [Current date and time](#current-date-and-time)
* [Frame - a container of elements](#frame---a-container-of-elements)
* [Table element](#table-element)
* [Image](#image)
* [Page break](#page-break)

### `Page Number` and `Page X of Y`

There is a predefined `Page Number` composite element, just a wrapper for `$V{PAGE_NUMBER}` variable. 

There is `Page X of Y` composite element. It consists of 2 elements, both rely on `$V{PAGE_NUMBER}` variable, 
but with different evaluation time.

### Current date and time

There are predefined `Current Date` and `Time` composite elements, both rely on locale of the document.

Most probably you want to control format, you could use explicit format for it as:

```xml
<textFieldExpression><![CDATA[new SimpleDateFormat("dd.MM.yyyy").format(new Date())]]></textFieldExpression>
```

### Frame - a container of elements

Sometimes it is convenient to group elements and move them together, you could use `Frame` element for this purpose.

### Table element

By default, table element has `Position Type = "Float"`. JR engine might move the whole table to another page, 
if there is not enough space for all lines. 

You might set the position type to "Fix Relative To Top". 

Then the table will be splitted and its header appear on both pages.

### Image

You pass image itself:
* via field, if you have an image per item/row or 
* via parameter, if you have an image per document

In both cases choose `java.awt.image.BufferedImage` class, but not `java.io.InputStream`.

The problems with `InputStream`:
* it is a challenge to close it correctly
* You cannot have more than a single image, if it is bind to `InputStream` on the document. 
For instance, you might to have logo on each page of the document.

To pass image for `logo`:

```xml
<parameter name="logo" class="java.awt.image.BufferedImage"/>
```

```java
import net.sf.jasperreports.engine.util.JRLoader;
import javax.imageio.ImageIO;

try (InputStream logo = Thread.currentThread().getContextClassLoader()
      .getResourceAsStream("images/logo.png")) {
  map.put(
  "logo",
  ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(logo))));
} catch (IOException | JRException e) {
  throw new IllegalStateException(e);
}
```

### Page break

You might have certain issues with page break.

For instance, you cannot use the whole area of the page after the page break.

It might be useful, after Page break create a new Detail Band, to avoid issue with a limit of the height of a band.

See details on: [Multiple pages that appear only once at the beginning of the report](../docs/Report_Templates.md#multiple-pages-that-appear-only-once-at-the-beginning-of-the-report)

