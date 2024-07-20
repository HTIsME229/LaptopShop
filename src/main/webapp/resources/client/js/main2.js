(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner(0);


    // Fixed Navbar
    $(window).scroll(function () {
        if ($(window).width() < 992) {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow');
            } else {
                $('.fixed-top').removeClass('shadow');
            }
        } else {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow').css('top', 0);
            } else {
                $('.fixed-top').removeClass('shadow').css('top', 0);
            }
        }
    });


    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({ scrollTop: 0 }, 1500, 'easeInOutExpo');
        return false;
    });


    // Testimonial carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 2000,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            576: {
                items: 1
            },
            768: {
                items: 1
            },
            992: {
                items: 2
            },
            1200: {
                items: 2
            }
        }
    });


    // vegetable carousel
    $(".vegetable-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            576: {
                items: 1
            },
            768: {
                items: 2
            },
            992: {
                items: 3
            },
            1200: {
                items: 4
            }
        }
    });


    // Modal Video
    $(document).ready(function () {
        var $videoSrc;
        $('.btn-play').click(function () {
            $videoSrc = $(this).data("src");
        });
        console.log($videoSrc);

        $('#videoModal').on('shown.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
        })

        $('#videoModal').on('hide.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc);
        })
    });



    // Product Quantity

    $('.quantity button').on('click', function () {
        let change = 0;
        var button = $(this);
        var oldValue = button.parent().parent().find('input ').val();
        if (button.hasClass('btn-plus')) {
            var newVal = parseFloat(oldValue) + 1;
            change = 1;
        } else {
            if (oldValue > 1) {
                var newVal = parseFloat(oldValue) - 1;
                change = -1;
            } else {
                newVal = 1;
            }
        }


        const currentQuantity = $('form').find('.currentQuantity');

        currentQuantity.val(newVal);
        console.log(currentQuantity.val(newVal))
        const input = button.parent().parent().find('input');
        input.val(newVal);
        const index = input.attr("data-cart-detail-index")
        const el = document.getElementById(`cartDetails${index}.quantity`);
        $(el).val(newVal);

        const price = input.attr("cd-price");
        const id = input.attr("cd-id");
        // let cd = JSON.parse('<%=cd%>');

        const priceElement = $(`p[cd-id='${id}']`);
        if (priceElement) {
            const newPrice = +price * newVal;
            priceElement.text(formatCurrency(newPrice.toFixed(2)) + " đ");

        }
        const totalPriceElement = $(`p[cd-total-price]`);

        if (totalPriceElement && totalPriceElement.length) {
            const currentTotal = totalPriceElement.first().attr("cd-total-price");
            let newTotal = +currentTotal;
            if (change === 0) {
                newTotal = +currentTotal;
            } else {
                newTotal = change * (+price) + (+currentTotal);
            }
            change = 0;

            //update
            totalPriceElement?.each(function (index, element) {
                //update text
                $(totalPriceElement[index]).text(formatCurrency(newTotal.toFixed(2)) + " đ");

                //update data-attribute
                $(totalPriceElement[index]).attr("cd-total-price", newTotal);
            });
        }

    });






    function formatCurrency(value) {
        // Use the 'vi-VN' locale to format the number according to Vietnamese currency format
        // and 'VND' as the currency type for Vietnamese đồng
        const formatter = new Intl.NumberFormat('vi-VN', {
            style: 'decimal',
            minimumFractionDigits: 0, // No decimal part for whole numbers
        });

        let formatted = formatter.format(value);
        // Replace dots with commas for thousands separator
        formatted = formatted.replace(/\./g, ',');
        return formatted;
    }


}


)(jQuery);
// <<<<<-----filter------->>>>>//////
const checkInput = document.querySelectorAll('.form-check-input');
const arr = Object.values(checkInput);

const filter = document.getElementById('filter')
let Link = "http://localhost:8080/products?page=1";
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
let URL_factoryNames = urlParams.get('factoryNames')
let URL_price_range = urlParams.get('price_range')
let URL_targetNames = urlParams.get('targetNames')
let URL_sortOptional = urlParams.get('sortOptional')
arr.forEach(e => {
    if (e.value == URL_factoryNames || e.value == URL_price_range || e.value == URL_targetNames || e.value == URL_sortOptional) {
        e.setAttribute("checked", "true");

    }
})
console.log(URL_price_range)
filter.onclick = (event) => {
    Link = "http://localhost:8080/products?page=1";
    let factoryNames = [];
    let price_range = [];
    let targetNames = [];
    let sort = "";
    let max = 0;
    let min = 0;

    arr.forEach(e => {

        if (e.checked && e.className.includes("factory")) {
            factoryNames.push(e.value)

            Link = `http://localhost:8080/products?page=1&factoryNames=${factoryNames}`;
        }
        else if (e.checked && e.className.includes("price_range")) {
            price_range.push(e.value)

            Link = `${Link}` + `&price_range=${price_range}`;
        }
        else if (e.checked && e.className.includes("target")) {
            targetNames.push(e.value);

            Link = `http://localhost:8080/products?page=1&factoryNames=${factoryNames}&targetNames=${targetNames}`;

        }
        else if (e.checked && e.className.includes("sort")) {
            sort = e.value;

            Link = `${Link}` + `&sortOptional=${sort}`;

        }




        filter.href = Link;



    })

};
const search = document.getElementById('search_button');
const checkInputSearch = document.querySelector('.search');
let link = "http://localhost:8080/products?page=1";
search.onclick = () => {
    link = `http://localhost:8080/products?page=1&nameOptional=${checkInputSearch.value}`;

    search.href = link;
}

checkInputSearch.onkeydown = (e) => {
    if (e.which == 13) {

        window.location.replace(`http://localhost:8080/products?page=1&nameOptional=${checkInputSearch.value}`);
    }



}




