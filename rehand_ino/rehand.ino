// #include "ArduinoBLE.h"

// Flex Sensor

int thumb;
int indexf;
int middle;
int ring;
int little;

// Take multiple samples to reduce noise
const int samples = 5;
const int bufferSize = 5;  // Size of the buffer for finding common values

int xBuffer[bufferSize];
int yBuffer[bufferSize];
int bufferIndex = 0;

// EMG Sensor

int emg = 0;

// BLE Setup

// const char * deviceServiceUuid = "00001101-0000-1000-8000-00805F9B34FB";
// const char * deviceServiceRequestCharacteristicUuid = "00001101-0000-1000-8000-00805F9B35FB";

void setup() {
  Serial.begin(19200);

  // BLE.setDeviceName("Rehand");
  // BLE.setLocalName("Rehand");

  // if(!BLE.begin()){
  //   Serial.println("Starting BLE Failed");
  // }

  // BLE.advertise();
}

void loop() {
  // BLEDevice peripheral = BLE.available();

  // Accelerometer
  // Read raw values
  int xRaw = 0, yRaw = 0;
  for (int i = 0; i < samples; i++) {
    xRaw += analogRead(A1);
    yRaw += analogRead(A2);
  }
  xRaw /= samples;
  yRaw /= samples;

  // Store values in the buffer
  xBuffer[bufferIndex] = xRaw;
  yBuffer[bufferIndex] = yRaw;

  bufferIndex++;

  // Reset buffer index if needed
  if (bufferIndex >= bufferSize) {
    bufferIndex = 0;

    // Once the buffer is full, calculate the most common values (mode)
    int xMode = findMode(xBuffer, bufferSize);
    int yMode = findMode(yBuffer, bufferSize);

    // Display the most common values
    Serial.print("X: ");
    Serial.print(xMode);
    Serial.print("\tY: ");
    Serial.println(yMode);
  }

  // EMG Sensor

  emg = analogRead(A0);     // Read EMG sensor value
  Serial.printf("EMG: 0");  // Print the value to the serial monitor

  // Flex Sensor

  thumb = analogRead(A7);
  indexf = analogRead(A6);
  middle = analogRead(A5);
  ring = analogRead(A4);
  little = analogRead(A3);

  // Print sensor values in a single line separated by commas
  Serial.print("Thumb :");
  Serial.println(max((thumb - 3580) / 5, 0));

  Serial.print("Index :");
  Serial.println(max((indexf - 3450) / 5, 0));

  Serial.print("middle :");
  Serial.println(max((middle - 3580) / 5, 0));

  Serial.print("ring :");
  Serial.println(max((ring - 3580) / 5, 0));

  Serial.print("little :");
  Serial.println(max((little - 3580) / 5, 0));

  delay(1000);
}

// Function to find the most common value (mode) in a buffer
int findMode(int* arr, int size) {
  int mode = arr[0];
  int maxCount = 0;

  for (int i = 0; i < size; i++) {
    int count = 0;
    for (int j = 0; j < size; j++) {
      if (arr[j] == arr[i])
        count++;
    }

    if (count > maxCount) {
      maxCount = count;
      mode = arr[i];
    }
  }

  return mode;
}