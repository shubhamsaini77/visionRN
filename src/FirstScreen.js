import React, { useState, useEffect } from 'react';
import { View, Text, Button, StyleSheet, PermissionsAndroid, Alert, Platform, TouchableOpacity } from 'react-native';
import axios from 'axios';
import BackgroundService from 'react-native-background-actions';

const FirstScreen = () => {

  const [location, setLocation] = useState(null);
  const [isSharing, setIsSharing] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // Request permissions
  const requestLocationPermission = async () => {
    if (Platform.OS === 'android') {
      const granted = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
        {
          title: 'Location Access Required',
          message: 'This app needs to access your location to provide better services.',
          buttonNeutral: 'Ask Me Later',
          buttonNegative: 'Cancel',
          buttonPositive: 'OK',
        }
      );
      return granted === PermissionsAndroid.RESULTS.GRANTED;
    }
    return false; // iOS permissions are handled automatically
  };

  //request the permission before starting the service.
  const requestBGLocationPermission = async () => {
    if (Platform.OS === 'android') {
      const backgroundgranted = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.ACCESS_BACKGROUND_LOCATION,
        {
          title: 'Background Location Permission',
          message:
            'We need access to your location ' +
            'so you can get live quality updates.',
          buttonNeutral: 'Ask Me Later',
          buttonNegative: 'Cancel',
          buttonPositive: 'OK',
        },
      );
      return backgroundgranted === PermissionsAndroid.RESULTS.GRANTED;
    }
    return true; // iOS permissions are handled automatically
  };





  // Start live location sharing
  const startLocationSharing = async () => {
    const hasPermission = await requestLocationPermission();
    if (!hasPermission) {
      Alert.alert('Permission denied', 'Location permission is required to share your live location.');
      return;
    }

    setIsSharing(true);
    setLoading(true);
    setError(null);
    //API Calling
    try {
      const url = "http://10.0.2.2:7707/api/v1/location/getCurrentLocation";
      // const response = await fetch(url2, { method: 'GET', headers });
      console.log("inside", url);
      const response = await axios.get(url, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'locale': 'en_IN'
        }
      });
      console.log(response.status);
      if (response.data.responseCode != 200) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      setLocation(response.data.responseObject);
      console.log("Success");
      console.log(response.data.responseObject);

    } catch (err) {
      Alert.alert("Error", err.message);
      console.log(err);
      setError('Failed to fetch location. Please try again.');
    } finally {
      setLoading(false); // Stop loading
    }

    // await new Promise( async (resolve) => {
    //   //for loop with a delay
    //   const { delay } = 50;
    //   console.log(BackgroundService.isRunning(), delay);
    //     for (let i = 0; BackgroundService.isRunning(); i++) {
    //       await BackgroundService.updateNotification({
    //         taskDesc: 'Runned ->' + i,
    //         progressBar: 2,
    //       });
    //         console.log(i);
    //         await sleep(delay);
    //     }
    // });
  };



  useEffect(() => {
    // Fetch location when the component loads
    startLocationSharing();
  }, []);



  // Save Location
  const sendLocationToServer = async (locationData) => {
    try {
      const url = "http://10.0.2.2:7707/api/v1/location/saveLocation";
      // const response = await fetch(url, {
        console.log("inside", url);
        const response = await axios.post(url,{
          id: locationData.id,
          latitude: locationData.latitude,
          longitude: locationData.longitude,
          locationTime: locationData.locationTime,
          accuracy: locationData.accuracy
        },
         {
        method: "POST",
        headers: {
          'Content-Type': 'application/json',
          'locale': 'en_IN'
        },
      });

      console.log(response.data.responseCode);
      if (response.data.responseCode != 200) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      console.log("Saved Success");

    } catch (error) {
      console.error(error);
      console.error('Failed to send location to server:', error.message);
      Alert.alert('Error', 'Failed to save location to the server.');
    }

    // await new Promise( async (resolve) => {
    //   //for loop with a delay
    //   const { delay } = 50;
    //   console.log(BackgroundService.isRunning(), delay);
    //     for (let i = 0; BackgroundService.isRunning(); i++) {
    //       await BackgroundService.updateNotification({
    //         taskDesc: 'Runned ->' + i,
    //         progressBar: 2,
    //       });
    //         console.log(i);
    //         await sleep(delay);
    //     }
    // });
  };



  // Stop live location sharing
  const stopLocationSharing = () => {
    setIsSharing(false);
    setLocation(null);
    console.log('Location sharing stopped.');
  };

  
  //Background
  const sleep = (time) => new Promise((resolve) => setTimeout(() => resolve(), time));

  BackgroundService.on('expiration', ()=> {
    console.log("Android: I am being closed!")
  })

  let playing = BackgroundService.isRunning();
  const options = {
    taskName: 'Example',
    taskTitle: 'ExampleTask title',
    taskDesc: 'ExampleTask description',
    taskIcon: {
        name: 'ic_launcher',
        type: 'mipmap',
    },
    color: '#ff00ff',
    linkingURI: 'yourSchemeHere://chat/jane', // See Deep Linking for more info
    parameters: {
        delay: 1000,
    },
};

  const toggleBackground= async () => {
    playing = !playing;
    if(playing) {
      try {
        console.log("Trying to start Background Service");
        await BackgroundService.start(startLocationSharing, options);
        console.log("Started");
      } catch (e) {
        console.log("Error", e);
      }
    } else {
      console.log("Stop Background Service");
      await BackgroundService.stop();
    }
  }

  //Foreground
  // ReactNativeForegroundService.add_task(() => update(), {
  //   delay: 1000,
  //   onLoop: true,
  //   taskId: "taskid",
  //   onError: (e) => console.log(`Error logging:`, e),
  // });

  // ReactNativeForegroundService.register = ({config: {alert, onServiceErrorCallBack}}) => {
  //   if (!serviceRunning) {
  //     setupServiceErrorListener({
  //       alert,
  //       onServiceFailToStart: onServiceErrorCallBack,
  //     });
  //     return ForegroundService.registerForegroundTask('myTaskName', startLocationSharing);
  //   }
  // };

  // const stopServiceAll = async () => {
  //   return await ForegroundServiceModule.stopServiceAll();
  // }

  // ReactNativeForegroundService.start({
  //   id: 1244,
  //   title: "Foreground Service",
  //   message: "We are live World",
  //   icon: "ic_launcher",
  //   button: true,
  //   button2: true,
  //   buttonText: "Button",
  //   button2Text: "Anther Button",
  //   buttonOnPress: "cray",
  //   setOnlyAlertOnce: true,
  //   color: "#000000",
  //   progress: {
  //     max: 100,
  //     curr: 50,
  //   },
  // });

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Live Location Sharing</Text>
      {location && (
        <View>
        <Text style={styles.location}>
          Latitude: {location.latitude}, Longitude: {location.longitude}
        </Text>
        <TouchableOpacity
        onPress={() => sendLocationToServer(location)}
        >
          <Text style={styles.saveLocation}>Save Location</Text>
        </TouchableOpacity>
        </View>
          
      )}
      {!isSharing ? (
        <Button title="Start Sharing" onPress={startLocationSharing} />
      ) : (
        <Button title="Stop Sharing" onPress={stopLocationSharing} />
      )}
    </View>
  );
}

export default FirstScreen

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f8f9fa',
    padding: 16,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  location: {
    fontSize: 16,
    marginBottom: 20,
  },
  saveLocation: {
    fontSize: 16,
    display: 'flex',
    width: 120,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'blue',
    color: '#fff',
    padding: 5,
    alignSelf: 'center',
    margin: 5
  }
});
