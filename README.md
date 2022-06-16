# space-pictures

Application uses NASA API portal - gets photos from 'APOD: Astronomy Picture of the Day' (https://api.nasa.gov/) 

Technologies:
compose, navigation, hilt(dagger), coroutines, mvvm, ktor, coil

3 screens:
- main list. begin from today, new photos downloads at reach last element. clickable image and description   
- image. zooming with gestures. app icon make return to main list 
- description. app icon make return to main list


![alt tag](https://github.com/severstal/space-pictures/blob/master/Screenshot_20220616_132759.png)
![alt tag](https://github.com/severstal/space-pictures/blob/master/Screenshot_20220616_132836.png)
![alt tag](https://github.com/severstal/space-pictures/blob/master/Screenshot_20220616_132823.png)

Application uses official api-key='DEMO_KEY' with limitations
For getting your own api-key see 'Generate API Key' on https://api.nasa.gov/ 
