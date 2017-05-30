app.controller('excelSheetCtrl', [ '$scope', '$route', 'fileUpload',
		function($scope, $route, fileUpload) {
			$scope.empInfo = false;
			$scope.attendance = false;
			$scope.empDetailsUploadFile = function() {
				$scope.showEmpMessage=false;
				$scope.empInfo = true;
				var file = $scope.myFile;
				$scope.disableAttendace = true;
				console.log('file is ');
				console.dir(file);

				var uploadUrl = "/uploadEmployeeDetails";
				fileUpload.uploadFileToUrl(file, uploadUrl, function(data) {
					
					if (data.data.success != null) {
						$scope.message=data.data.success;
						$scope.showEmpMessage=true;
						$scope.disableAttendance = false;
						$scope.empInfo = false;
					}

				});

			};

			$scope.attendanceUploadFile = function() {
				$scope.showAttendanceMessage=false;
				var file = $scope.myFile;
			
				$scope.attendance = true;
				$scope.disableEmpInfo = true;
				console.log('file is ');
				console.dir(file);

				var uploadUrl = "/uploadAttendenceDetails";
				fileUpload.uploadFileToUrl(file, uploadUrl, function(data) {
					if (data.data.success != null) {
						$scope.showAttendanceMessage=true;	
						$scope.message=data.data.success;
						$scope.disableEmpInfo = false;
						$scope.attendance = false;
					}
				});
			};
			$scope.clear = function() {
				
				window.location.reload();
			}
		} ]);