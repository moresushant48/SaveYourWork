/**
 * Request handler for Listing their files to user and access/download/delete management.
 */
var tblUserFiles;

// delete file.
function request(e,event){
	
	event.preventDefault();
	$.get($(e).attr('href'),
    	function (data, textStatus, jqXHR) {
		tblUserFiles.ajax.reload();
    });
}

function feedUserFilesTable(uid) {
	tblUserFiles = $('#userFilesTable').DataTable({
		"sAjaxSource": "/rest/list-files/" + uid,
		"sAjaxDataProp": "",
		"order": [ 1, "asc" ],
		"columnDefs": [{"targets":[0,3], "orderable": false, "searchable": false}],
		"language": {
			"emptyTable": "<h1 class='text-center lead m-3'> <i class='fa fa-frown-o fa-4x'></i><br>You don't have any files here.<br> Upload some.</h1>"
		},
		"aoColumns": [
			{ "mRender" : function (data, type, row) {
				switch(row.access.access) {
					
				case 'PRIVATE':
					return $('#access-template').html() + '<h6 class="dropdown-header text-success">'+ row.access.access +'</h6>'
					+ '<div class="dropdown-divider"></div>' 
					+ '<a id="req" href="/list-files/public?fileId='+ row.id +'" onclick="request(this,event)" class="btn btn-danger" style="width: 100%">Public</a><br>'
					+'<a id="req" href="/list-files/protected?fileId='+ row.id +'" onclick="request(this,event)" class="btn btn-primary" style="width: 100%">Protected</a></div></div>';
				
				case 'PUBLIC':
					return $('#access-template').html() + '<h6 class="dropdown-header text-danger">'+ row.access.access +'</h6>'
					+ '<div class="dropdown-divider"></div>' 
					+ '<a id="req" href="/list-files/private?fileId='+ row.id +'" onclick="request(this,event)" class="btn btn-success" style="width: 100%">Private</a><br>'
					+'<a id="req" href="/list-files/protected?fileId='+ row.id +'" onclick="request(this,event)" class="btn btn-primary" style="width: 100%">Protected</a></div></div>';
				
				case 'PROTECTED':
					return $('#access-template').html() + '<h6 class="dropdown-header text-primary">'+ row.access.access +'</h6>'
					+ '<div class="dropdown-divider"></div>' 
					+ '<a id="req" href="/list-files/public?fileId='+ row.id +'" onclick="request(this,event)" class="btn btn-danger" style="width: 100%">Public</a><br>'
					+'<a id="req" href="/list-files/private?fileId='+ row.id +'" onclick="request(this,event)" class="btn btn-success" style="width: 100%">Private</a></div></div>';
				default:
					return "undefined";
				}             		
			}},
		  	{ "mData": "fileName" },
			{ "mData": "fileSize" },
			{ "mRender" : function (data, type, row) {
				
		       return '<a href="/uploads/' + row.fileName + '" class="fa fa-download fa-lg text-success mr-4"></a>' +
		       		'<a id="req" href="/delete-file?id=' + row.id + '&name=' + row.fileName + '" onclick="request(this,event)" class="fa fa-trash fa-lg text-danger"></a>';
		                   		
			}}
		]
				
	 });
}