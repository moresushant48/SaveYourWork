/**
 * List the feedbacks from users.
 */
var tblFeedbacks;

// delete file.
function requestFeedbacks(e,event){
	
	event.preventDefault();
	$.get($(e).attr('href'),
    	function (data, textStatus, jqXHR) {
		tblFeedbacks.ajax.reload();
    });
}

function feedFeedbacksTable() {
	tblFeedbacks = $('#feedbacksTable').DataTable({
		"sAjaxSource": "/admin/userFeedback/get",
		"sAjaxDataProp": "",
		"order": [ 0, "desc" ],
		"columnDefs": [{"targets":[2], "orderable": false, "searchable": false}],
		"language": {
			"emptyTable": "<h1 class='text-center lead m-3'> <i class='fa fa-frown-o fa-4x'></i><br>You don't have any feedbacks yet.</h1>"
		},
		"aoColumns": [
		  	{ "mData": "id" },
			{ "mData": "message" },
			{ "mRender" : function (data, type, row) {
				
		       return '<a href="/admin/delete-feedback?id=' + row.id + '" onclick="requestFeedbacks(this,event)" class="fa fa-trash fa-lg text-danger"></a>';
		                   		
			}}
		]
				
	 });
}