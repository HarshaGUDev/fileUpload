      app.service('fileUpload', ['$http', function ($http) {
            this.uploadFileToUrl = function(file, uploadUrl,cb){
               var fd = new FormData();
               fd.append('file', file);
           
               $http.post(uploadUrl, fd, {
                  transformRequest: angular.identity,
                  headers: {'Content-Type': undefined}
               })
               .then(function(response) {
                   
            	   cb(response);
                  
               })
               .catch(function(){
               });
            }
            
        
         }]);