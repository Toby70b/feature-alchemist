<h1>Feature Alchemist</h1>

<h2>Purpose</h2>
<p>
The purpose of this project was to create a tool that could deserialize a Cucumber .feature file into a format which 
would be easier to consume by other tools which would perform a series of validation and modification to the 
deserialized feature object before finally re-serializing the object back into a .feature file.
</p>

<h2>Background</h2>
<p>
At work there was an idea that to rely more on cucumber tags to provide testing targeted at more specific areas of 
functionality. To support this several tools were conceived that could validate tags were being used, could modify 
tags, and could gather tags for reporting purposes. Each of the considered tools would require some tool that could 
first deserialize a .feature file into something easier for the tools to work with.
</p>

<p>
Gherkin already provided a tool that could deserialize an .feature file into a POJO. However, once the modifications 
were performed we needed a way to re-serialize the POJO back into a .feature file.
</p>

<p>
However due to resources and low priority the project never really got going. However, I was interested in making a 
proof-of-concept (POC) for how the tool could look in my spare time. As the project was cancelled, I didn’t have much 
enthusiasm to continue, especially as I became interested in other projects. Since it was done in my spare time I 
created this repo so as not to lose the work.
</p>


<h2>How to use</h2>

<h2>Improvements</h2>
As mentioned above this project was a proof-of-concept and some logic like the feature object validation necessitating 
the use of tags in scenarios reflects some business rules from the original design document. As such there are 
some things that could be improved. I don't anticipate returning to this project soon, but here are some things in case future me is interested.
<ul>
    <li>More unit tests around feature serialization</li>
    <li>Refactoring the "Builder" classes to not use on static methods
    <ul>
      <li>A single class responsible for building the various methods</li>
      <li>Create a "Serializable" interface (though that specific name is taken) and implement 
         it in the various model classes like Feature, Scenario.
      </li>
      <li>Keep the multiple "Builder" classes but remove the static keyword, as per my general dislike of static methods</li>
    </ul>
    </li>
    <li>Greater configurability of validation, allow the client to construct a custom validation object to use during serialization</li>
</ul>
