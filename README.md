# Calculator with Socket Stream

![N|Solid](https://image.winudf.com/v2/image/bWUuYWZsYWsuY2xpZW50X2ljb25fMF80ZjBiMmQzOA/icon.png?w=170&fakeurl=1&type=.png)

### Exposition

> This code shows how it works
> between a server and a client using the TCP protocol. 
> The calculator is configured to perform simple operations.


### Working
**TCP configuration**
```sh
127.0.0.1:5555
```

**Threads**

| Thread 1 | Thread 2 |
| ------ | ------ |
| Client | Server |
| Interface  | Operations |

**A client that will send requests**
```kotlin
var adder: InetSocketAddress = InetSocketAddress("127.0.0.1", 55555)
var clientSocket: Socket = Socket()
clientSocket = connect(adder)

var exit: OutputStream = clientSocket.getOutputStream()
exit.write("message to send".toByteArray())
var entry: InputStream = clientSocket.getInputStream()

```

**A server that will always be listening**

```kotlin
while(condition){

    ServerSocket().use { serverSocket ->
        var adder: InetSocketAddress = InetSocketAddress("127.0.0.1", 55555)
        serverSocket.bind(adder)
    
        serverSocket.accept().use { newSocket ->
            var exit: OutputStream = newSocket.getOutputStream()
            var entry: InputStream = newSocket.getInputStream()
        }
    }
}
```


**The type of incoming object is defined**
```kotlin
val resultBytes = ByteArray(25)
entry.read(resultBytes)
```

# Demostration
<img src="demostration.gif" />