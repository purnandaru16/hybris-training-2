if (!window.training) {
    window.training = {};
}
var training = window.training;

(function ($) {

    training.global = {
        home: function () {
            var mainBanner = $(".main-banner");

            mainBanner.slick({
                dots: true,
                infinite: true,
                speed: 300,
                slidesToShow: 1,
                arrows: true,
                adaptiveHeight: true
            });
        },

        init: function () {
            var _self = training.global;
            _self.home();
        }
    };
    training.global.init();

})(jQuery);