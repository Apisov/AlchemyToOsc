# AlchemyToOsc
Simple start of extracting features from Alchemy text analysis API

The main idea is using an API of service which provides various possibilities of text analysis and fetching interesting info.

Now it's using this service:
http://docs.alchemyapi.com/docs

The AlchemyData News API provides highly targeted search, trend analysis and historical access to news and blog content with access to 75,000 unique sources, 250,000 new daily articles and 60 days of historical access.

You can test it here:
http://querybuilder.alchemyapi.com/builder

Currently it's fetching 10 articles by "concept" word\sentence and calculating average of sentiments between them.

We have 3 inputs:
1) averageMixed: from 0 to 1

mixed means that sentiment not only positive or some another rather contain both of them.

2) averageScore: from -1 to 1
score  means how much in percentege. For example it should be approximately from 0.1 till 1 to be positive sentiment. 

3) and one of 3 types of sentiments(NEGATIVE, POSITIVE,NEUTRAL) in values it will be -1, 1, 0

For now...it's very at the begining, totally not finished. But if someone will be interested in this we can work togherther on finishing.

Also the biggest problem on work with this project - limitation of Alchemy service. Only 1000 requsts per day in FREE plan.


For building this project you need Idea and Java:
https://www.jetbrains.com/idea/

After downloading and instaling Idea you can just import this project.
