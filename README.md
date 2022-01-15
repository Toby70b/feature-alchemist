<h1>QA Feature Alchemist</h1>

<h2>Purpose</h2>
<p>This project was created to provide a common interface for converting .feature files into objects and vice-versa.</p>

<h2>Latest release</h2>
<p>To include this dependency in your project:</p>

```
<dependency>
    <groupId>com.pps.qa</groupId>
    <artifactId>qa-feature-alchemist</artifactId>
    <version>1.0.0</version>
</dependency>
```

<h2>How to Use</h2>

<p>To decompile .feature files into a Feature object:</p>

```
  Feature feature = QaFeatureAlchemist.decompile(new File("src/main/resources/feature/blackhawk/ums/DigitalAccountRequest.feature"));
```

<p>To re-compile Feature objects back into Feature files:</p>

```
  File file = FeatureCompiler.convertObjectIntoFeatureFile(feature, "newFeature");
```

<h2>Improvements</h2>
<ul>
    <li>Unit tests should be created for the FeatureCompiler (The FeatureDecompiler is from Gherkin so less of a priority)</li>
    <li>Better formatting on conversion from Java object to .feature file.</li>
</ul>

<h2>Documentation</h2>

<p>Confluence Documentation can be found <a href="https://prepaysolutions.atlassian.net/wiki/spaces/DEVQA/pages/3821732229/">here</a>.</p>