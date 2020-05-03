/**
 * Request handler for Listing users for admin.
 */
var tblUsers; 

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
				
		       return '<input id="uid" hidden="hidden" type="text" value="' + row.id + '" />' +
		       		'<a href="/admin/manage-role?id=' + row.id + '" class="fa fa-edit fa-lg text-primary mr-4"></a>' +
		       		'<a href="#" class="admin-del-user fa fa-trash fa-lg text-danger"></a>';
		                   		
			}}
		]
				
	 });
}

$(document).on('click','.admin-del-user',function(e) {
	e.preventDefault();
    bootbox.confirm({
        title: "Confirm",
        message: "Are your sure you want to delete your account? <br> <p class='text-muted'>Note : This cannot be undone.</p>",
        backdrop: true,
        centerVertical: true,
        callback: function(result) { if(result) deleteUser() }
    });
});

function deleteUser(){
	
	$.get("/user/account/delete/" + $('#uid').val(), function(data, textStatus, jqXHR) {
		if(data){
			tblUsers.ajax.reload();
		}
	});
}