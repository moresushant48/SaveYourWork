/**
 * Request handler for Listing users for admin.
 */
var tblUsers;

// delete file.
function requestUsers(e,event){
	
	event.preventDefault();
	$.get($(e).attr('href'),
    	function (data, textStatus, jqXHR) {
		tblUsers.ajax.reload();
    });
}

function feedUsersTable() {
	tblUsers = $('#usersTable').DataTable({
		"sAjaxSource": "/admin/list-users/get",
		"sAjaxDataProp": "",
		"order": [ 0, "desc" ],
		"columnDefs": [{"targets":[3], "orderable": false, "searchable": false}],
		"language": {
			"emptyTable": "<h1 class='text-center lead m-3'> <i class='fa fa-frown-o fa-4x'></i><br>You don't have any users yet.</h1>"
		},
		"aoColumns": [
		  	{ "mData": "id" },
			{ "mData": "username" },
			{ "mData": "email" },
			{ "mRender" : function (data, type, row) {
				
		       return '<a href="/admin/manage-role?id=' + row.id + '" class="fa fa-edit fa-lg text-primary mr-4"></a>' +
		       		'<a href="/admin/delete-user?id=' + row.id + '" onclick="requestUsers(this,event)" class="fa fa-trash fa-lg text-danger"></a>';
		                   		
			}}
		]
				
	 });
}