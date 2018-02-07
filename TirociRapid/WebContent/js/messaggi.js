function clearMessaggiTop()
{
	$("h1.erroreTop").remove();
	$("h1.successoTop").remove();
}

function nascondiMessaggiTop()
{
	setTimeout(clearMessaggiTop, 5000);
}