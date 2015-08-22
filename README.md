# RxAndroidVideoSearch
Simple RxAndroid application that allows a user to search a video title and view video information relating to that title.
I created this application to get a first look at the reactive methodology within the Android platform.

## Takeaways
- RxAndroid guides and helps the developer create a clean Model View Presenter(MVP) architecture while easily decoupling the view and model.
- RxAndroid at first is challenging
  - In order to get a clear understanding of the Rx methodology I did some extensive research online. The main drawback to this programming methogology is the large learning curve.
  - Once I started to develop using samples the methodology became clearer and easier to understand. Essentially the methodology follows closely to the observer pattern.

## Generalized MVP Structure
- The structure of the application as stated above is MVP.
  - The model holds the data acting as a Content Provider of sorts.
  - The presenter acts as the glue between the model and the view allowing the view to receive a callback when data is available
  - The view is responsible for updating the UI when it receives a callback from the presenter. 

## Generalized Rx Methodology
- What is Rx? 
  - Represents any type of data as an asynchronous data stream created on any thread, functionaly transformed and then consumed by all subscribers on any other thread.
  - This is saying that any form or type of data typically a stream of some sort like a request or list of requests can be represented as an asynchronous stream. These are refered to as <b>Observables</b>, or where our data is coming from.
  - The Observables (data streams) are then created on any other thread through the use of a <b>Scheduler</b> to do some sort of work or action like executing a REST request for data.
  - The data collected from the Observable is then functionaly transformed (modified without changing our source) by an <b>Operator</b> or Operators
  - Finally the data is sent to and consumed by the <b>Subscribers</b> (those listening for the data, typically our main thread is one). Again this action of scheduling a thread as a Subscriber and receiving updates on data events is done by the Scheduler.
  - To sum it up , the Observable is created on any thread by a Scheduler to do work, Operator transforms or parses the data, Subscribers consume the data.
- Advantages
  - Easy decoupling of code.
  - Status/lifecycle reporting on an Observable, such as onNext, onError, onComplete
  - Makes code easier to read
- Disadvantages
  - Newish, many developers still do not know this methodology, therefore writing an API in Rx may not be the best idea.
  - Semi-difficult methodology to understand if one does not have experience in the callback or observer pattern.
