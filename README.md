The idea is to create a basic App that demonstrates the power of Retrofit Library compared to the old fashion more cumbersome technique of OkHttp with AsyncTask of Android SDK.

I will read data from this json url about Earthquakes:

https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02

I will show basic information such as location, name time in a RecyclerView

My steps:

1. Adding Retrofit dependency:
Android has already provided us classes to deal with backend API such as HttpManager and AsyncTask to handle network communication. But this is very complex and time consuming because we need expert skills level to handle complex communications. Retrofit is "type safe" Rest client for Android. I don’t have to worry about parsing complex json structures as when using OkHttp directly, but Retrofit will do it for me 
implementation 'com.squareup.retrofit2:retrofit:2.1.0'

2. Add Gson converter to parse the Json to. Retrofit automatically serialises the JSON response into Java class which must be defined in advanced for the JSON Structure
implementation 'com.google.code.gson:gson:2.8.5'
implementation 'com.squareup.retrofit2:converter-gson:2.1.0'

3. Add Internet permission to manifest 
<uses-permission android:name="android.permission.INTERNET"/>

4. Open the url of the json in a json browser viewer to have a Idea about the size of data and the structure
https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02

5. I generate classes using some online tools such as this http://pojo.sodhanalibrary.com/ . Those online converters create Java classes for us using a json, but cannot generate classes for the jsons inside jsons, so we need to be aware of structure of the mother json and copy-paste its inner jsons and convert them individually to classes. Those inner jsons will be considered as inner classes in java. So the root json is one huge class and the sons jsons are inner classes.

6. Better to convert / make those classes in Kotlin because we get rid of more than half of the size because there will be no getters & setters and the visibility of the structure will be better than Java.

7. For my case, one root json is represented by EarthquakeRoot.java class that ha:
- inner class Metadata
- inner class Features

and inner class Features will have 2 other inner classes:
- inner class Geometry
- inner class Properties

8. I create basic architecture for Retrofil made of 3 packages
- model
- service
- view

9. I put the Java class representing the json structure in the “model” package. It is the place where we put all model classes that represent json structures

10. The “view” package is where activities and App related views and their components should be located (ex: RecuclerView stuff)

11. The “service” package is where the operations, classes & interface related to the Retrofit are located. We can also call this package “network”

12. In every project that uses Retrofit, we need to have an Interface that holds all the different Http Requests signatures that we want to use in our App. Requests can be Get or Post for example. It is also the place where we define the End-points that should be added to our base-url. A simple example in our context here is this:
Base Url = https://earthquake.usgs.gov/
And End-Point with all parameters:
fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02
The Interface defines some methods that perform HTTP requests with annotation. Also the type of requests and the model class (that we have already created in Kotlin) that should parse the response that we wrote previously

13 Create a class that allows us to get an initialized Retrofit instance that uses the base-url and that later we can use it to call the methods that we wrote or we will write later in our Interface. I call Retrofit Instance class RetrofitInstance and the interface RetrofitCalls.

14. I write only 1 method in the RetrofitInstance class and it allows me to get an initialized version of the Retrofit instance. I call the method: setupRetrofitCalls(). It will return the single instance in our App RetrofitCalls

15. Now I can call this static method from anywhere and return  from it a RetrofitCalls which I will use to call any the methods that I wrote in the interface and that will do different http calls. 

16. I created a data container of type ArrayList that will hold all the data returned by the retrofit request on success. This ArrayList is used by the RecyclerView holder to bind data to the views or every single Item in the RecyclerView. Sure also the time of the eartjhquake is timestamp so I converted it to human readable

17. The recyclerView holder gets reference to the component of the Item model used to fill the RecyclerView. I used CardView as container design for items.

18. I created some UX progress bar feedback for waiting when the Retrofit Request is being processed before sending the response to the activity.
