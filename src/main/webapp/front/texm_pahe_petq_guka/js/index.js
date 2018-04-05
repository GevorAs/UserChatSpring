$(function(){

  var status = $(".status"),
      postForm = $(".post-form"),
      postButton = $(".submit"),
      post = $(".post"),
      postCtr = $(".post-ctr"),
      postStatus = $(".post-status");

  var tl = new TimelineMax();

  status.on("keyup", function(){
    var statusAmount = $(this).val().length;
    if(statusAmount > 0){
      postButton.removeClass("not-active");
      post.addClass("glance");
    }
    else {
      postButton.addClass("not-active");
      post.removeClass("glance");
    }
  });

  postButton.on("click", function(){
    $(this).val("Posting...").addClass("not-active");
    var dataPostStatus = status.val();
    postStatus.text(dataPostStatus);

    tl
      .to(post, .15, {
        delay: .15,
        y: "10px",
        opacity: .5
      })
      .to(post, .3, {
        y: "0",
        scale: 1
      })
      .to(post, .3, {
        opacity: 1,
        onComplete: reset
      })
  });

  function reset(){
    postButton.val("Post").removeClass("not-active");
    status.val("");
  }


  // copy
  balapaCop("Status Update Interaction", "#999");
  
})();