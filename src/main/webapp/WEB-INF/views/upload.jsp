<%--
  Created by IntelliJ IDEA.
  User: hung1
  Date: 8/18/2023
  Time: 7:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/UploadController" method="post" enctype="multipart/form-data">
    <input type="file" id="uploadButton" multiple>
    <progress id="uploader" value="0" max="100"></progress>
    <div id="list_videos"></div>
    <input type="text" name="video_url" id="video_url">
    <input type="submit" name="action" value="Upload">
</form>
<script src="https://www.gstatic.com/firebasejs/4.2.0/firebase.js"></script>
<script>
    const firebaseConfig = {
        apiKey: "AIzaSyCWGBoPh7eLVjrbffK4Dwvhkf-VN6t_9_o",
        authDomain: "javaweb-bfc38.firebaseapp.com",
        projectId: "javaweb-bfc38",
        storageBucket: "javaweb-bfc38.appspot.com",
        messagingSenderId: "826519421173",
        appId: "1:826519421173:web:5e808e1ab7fa042e83c5bc",
        measurementId: "G-ETDWFJN0PK"
    };
    firebase.initializeApp(firebaseConfig);
    var image ;
    // firebase bucket name
    // REPLACE WITH THE ONE YOU CREATE
    // ALSO CHECK STORAGE RULES IN FIREBASE CONSOLE
    var fbBucketName = "images";


    //   forrm ADD
    // get elements
    // ther progess
    var uploaderAdd = document.getElementById("uploader");
    // ther input file
    var fileButtonAdd = document.getElementById("uploadButton");
    console.log(uploaderAdd);
    console.log(fileButtonAdd);

    // listen for file selection
    fileButtonAdd.addEventListener("change", function (e) {
        //Get files
        image = [];
        for (var i = 0; i < e.target.files.length; i++) {
            var imageFile = e.target.files[i];

            uploadImageAsPromise(imageFile);
        }
    });

    //Handle waiting to upload each file using promise
    function uploadImageAsPromise(imageFile) {
        return new Promise(function (resolve, reject) {
            var storageRef = firebase
                .storage()
                .ref(fbBucketName + "/" + imageFile.name);

            //Upload file
            var task = storageRef.put(imageFile);

            //Update progress bar
            task.on(
                "state_changed",
                function progress(snapshot) {
                    var percentage =
                        (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
                    uploaderAdd.value = percentage;
                },
                function error(err) {},
                function complete() {
                    var downloadURL = task.snapshot.downloadURL;
                    console.log("dowload URL --->", downloadURL);
                    image.push(downloadURL);
                    let divLocation = document.getElementById("list_videos");
                    let videoElement = document.createElement("video");
                    videoElement.src = downloadURL;
                    videoElement.width = "300";
                    videoElement.height= "200";
                    videoElement.controls = "controls";
                    videoElement.style.objectFit= "cover";
                    divLocation.append(videoElement);

                    // khi upload thành công
                    document.getElementById("video_url").value = image;
                }
            );
        });
    }
    function getImage() {
        return image;
    }
</script>
</body>
</html>
