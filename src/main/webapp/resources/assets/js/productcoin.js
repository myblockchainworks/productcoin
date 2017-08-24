/**
 * 
 */

function formatDate(value, className) {
	var date = new Date(parseInt(value));
	var monthNames = [
	  "Jan", "Feb", "Mar",
	  "Apr", "May", "Jun", "Jul",
	  "Aug", "Sep", "Oct",
	  "Nov", "Dec"
	];
	
	var day = date.getDate();
	var monthIndex = date.getMonth();
	var year = date.getFullYear();
	
	var formatted = day + '-' + monthNames[monthIndex] + '-' + year;
	$('.' + className).html(formatted);
}

function getStatus(value, className) {
	var status = '';
    if (value == 0) {
      status = '<span class="created">ACTIVE</span>';
    } else if (value == 1) {
      status = '<span class="firstpayoutdue">FIRSTPAYOUTDUE</span>';
    } else if (value == 2) {
      status = '<span class="nextpayoutdue">NEXTPAYOUTDUE</span>';
    } else if (value == 3) {
      status = '<span class="expired">EXPIRED</span>';
    }
    $('.' + className).html(status);
}

function showPayoutButton(value, className, currentindex) {
	var payoutButton = '';
   if (value == 1) {
    	payoutButton = '<a href="processPayout?currentindex=' + currentindex + '" class="buttontype">Process First Payout</a>';
    } else if (value == 2) {
    	payoutButton = '<a href="processPayout?currentindex=' + currentindex + '" class="buttontype">Process Next Payout</a>';
    } 
    $('.' + className).html(payoutButton);
}
