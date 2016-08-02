jQuery.noConflict();

jQuery(document).ready(function(){
	
	/**
	 * This will remove username/password text in the login form fields
	**/
	jQuery('.username, .password').focusout(function(){
		if(jQuery(this).val() != '') {
			jQuery(this).css({backgroundPosition: "0 -32px"});	
		} else {
			jQuery(this).css({backgroundPosition: "0 0"});	
		}
	});
	
	jQuery('.username, .password').focusin(function(){
		if(jQuery(this).val() == '') {
			jQuery(this).css({backgroundPosition: "0 -32px"});	
		}
	});
	
	
	/**
	 * Message Notify Drop Down
	**/
	jQuery('.messagenotify .wrap, .alertnotify .wrap').click(function(){
		var t = jQuery(this).parent();
		var url = t.attr('href');
		if(t.hasClass('showmsg')) {
			t.removeClass('showmsg');
			t.find('.thicon').removeClass('thiconhover');
			t.parent().find('.dropbox').remove();
			
		} else {
			
			jQuery('.topheader li').each(function(){
				jQuery(this).find('.showmsg').removeClass('showmsg');
				jQuery(this).find('.thicon').removeClass('thiconhover');
				jQuery(this).find('.dropbox').remove();
			});
			
			t.addClass('showmsg');
			t.find('.thicon').addClass('thiconhover');
			t.parent().append('<div class="dropbox"></div>');
						
			jQuery.post(url,function(data){
				jQuery('.dropbox').append(data);
			});
		}
		return false;
		
	});
	
	jQuery(document).click(function(event) {
		var msglist = jQuery('.dropbox');
		if(!jQuery(event.target).is('.dropbox')) {
			if(msglist.is(":visible")) {
				msglist.prev().removeClass('showmsg');
				msglist.prev().find('.thicon').removeClass('thiconhover');
				msglist.remove();
			}
		}        
	});

	
	/**
	 * Login form validation
	**/
	jQuery('#loginform').submit(function(){
		var username = jQuery('.username').val();
		var password = jQuery('.password').val();
		if(username == '' && password == '') {
			jQuery('.loginNotify').slideDown('fast');	
			return false;
		} else {
			return true;
		}
	});
	
	
	/**
	 * Sidebar accordion
	**/
	jQuery('#accordion h3').click(function() {
		if(jQuery(this).hasClass('open')) {
			jQuery(this).removeClass('open');
			jQuery(this).next().slideUp('fast');
		} else {
			jQuery(this).addClass('open');
			jQuery(this).next().slideDown('fast');
		} return false;
	});
		
	
	/**
	 * Widget Box Toggle
	**/
	jQuery('.widgetbox h3, .widgetbox2 h3').hover(function(){
		jQuery(this).addClass('arrow');
		return false;
	},function(){
		jQuery(this).removeClass('arrow');
		return false;
	});
	
	jQuery('.widgetbox h3, .widgetbox2 h3').toggle(function(){
		jQuery(this).next().slideUp('fast');
		jQuery(this).css({MozBorderRadius: '3px', 
						  WebkitBorderRadius: '3px',
						  borderRadius: '3px'});
		return false;
	},function(){
		jQuery(this).next().slideDown('fast');
		jQuery(this).css({MozBorderRadius: '3px 3px 0 0', 
						  WebkitBorderRadius: '3px 3px 0 0',
						  borderRadius: '3px 3px 0 0'});
		return false;
	});
	
	
	/**
	 * Notification
	**/
	jQuery('.notification .close').click(function(){
		jQuery(this).parent().fadeOut();
	});	
	
	
	/** Make footer always at the bottom**/
	if(jQuery('body').height() > jQuery(window).height()) {
		jQuery('.footer').removeClass('footer_float');
	}
	
	
	
	/**DROP DOWN MENU**/
	jQuery(".subnav").css({display: "none"}); // Opera Fix
	jQuery(".tabmenu li").hover(function(){
		jQuery(this).find('ul:first').css({visibility: "visible",display: "none"}).show(400);
	},function(){
		jQuery(this).find('ul:first').css({visibility: "hidden"});
	});
	/******删除的js******/
	/**
   * Highlight selected table row
  **/
  jQuery('.sTable input').click(function(){
    
    if(jQuery(this).is(':checked')) {
      jQuery(this).parents('tr').addClass('selected');
    } else {
      jQuery(this).parents('tr').removeClass('selected'); 
    }
  });
  
  
  /**
   * Delete a single user in a row
  **/
  jQuery('.deleteuser').click(function(){
    var c = confirm("Are you sure you want to delete this user?");
    if(c) {
      jQuery(this).parents('tr').fadeOut();
    }
    return false;
  });
  
  /**
   * Check/Uncheck all items in a table
  **/
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
  
  
  
  /**
   * Delete selected items in a table
  **/
  jQuery('.sTableOptions .delete').click(function(){
    var empt = true;
    jQuery('.sTable input[type=checkbox]').each(function(){
      if(jQuery(this).is(':checked')) {
        empt = false;
      }
    });
    if(empt == true) {
      alert('没有选择任何一项');
    } else {
      var c = confirm('确定删除吗?');
      if(c) {
        jQuery('.sTable input[type=checkbox]').each(function(){
          if(jQuery(this).is(':checked')) {
            jQuery(this).parents('tr').fadeOut();
          }
        });
      }
    }
  });
/******删除的js******/

});