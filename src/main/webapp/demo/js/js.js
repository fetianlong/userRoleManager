// JavaScript Document

//$(function(){
jQuery(document).ready(function(){
	//全选
	jQuery('.checkall').click(function(){
	   if(!jQuery(this).is(':checked')) {
	     jQuery('.sTable input[type=checkbox]').each(function(){
	       jQuery(this).attr('checked',false);
	       jQuery(this).parents('tr').removeClass('selected');
	     });
	   } else {
	     jQuery('.sTable input[type=checkbox]').each(function(){
	       jQuery(this).attr('checked',true);
	       jQuery(this).parents('tr').addClass('selected');
	     });
	   }
	});
	
	//单选
	jQuery('.sTable input').click(function(){
//		if ($(this).prop("checked") == true) {
//            $("input[type='checkbox']").attr("checked", false);
//            $(this).prop("checked", true);
//        }
		if(jQuery(this).is(':checked')) {
			jQuery(this).parents('tr').addClass('selected');
		} else {
			jQuery(this).parents('tr').removeClass('selected'); 
		}
	});
})