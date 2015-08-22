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

