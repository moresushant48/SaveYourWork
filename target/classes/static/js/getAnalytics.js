/**
 * Get the website analytics in every few seconds.
 */

function getAnalytics() {
	$.ajax({
		url: '/admin/analytics/get',
		type: 'get',
		success: function(response) {
			
			$("#totalCustomers").text(response.totalCustomers);
			$("#totalFiles").text(response.totalFiles);
			$("#totalFeedbacks").text(response.totalFeedbacks);
			$("#totalOnline").text(response.totalOnline);
		}
	});
}