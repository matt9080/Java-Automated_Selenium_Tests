package pageObjects;

public class scanLocators {

    // URLS
    final String SCAN_HOME_URL = "https://www.scanmalta.com/newstore/";
    final String SCAN_LOGIN_URL = "https://www.scanmalta.com/newstore/customer/account/login/";
    final String SCAN_LOGGED_IN_DASHBOARD = "https://www.scanmalta.com/newstore/customer/account/index/";
    final String SCAN_SHOPPING_CART_URL = "https://www.scanmalta.com/newstore/checkout/cart/";
    final String SCAN_PRODUCT_DETAILS_URL = "https://www.scanmalta.com/newstore/microsoft-surface-pro-7-123-win10-pro-core-i3-128gb-ssd-4gb-platinum-tablet.html";

    // IDS
    // LOGIN
    final String LOGIN_EMAIL_ID = "email";
    final String LOGIN_PASSWORD_ID = "pass";
    final String LOGIN_SUBMIT_BUTTON = "send2";
    final String INCORRECT_EMAIL_NOTIFICATION = "advice-validate-email-email";
    final String INCORRECT_PASSWORD_NOTIFICATION = "advice-validate-password-pass";

    // SEARCH
    final String SEARCH_BAR_ID = "search";
    final String PRODUCT_UL_CONTAINER_ID = "catalog-listing";

    //PRODUCT DETAILS
    final String ADD_TO_CART_ID = "product-addtocart-button";

    // SHOPPING CART
    final String SHOPPING_CART_EMPTY_ID = "empty_cart_button";

    //CLASS
    //SEARCH
    final String PRODUICT_LI_SINGLE_CLASS = "product-image";
    //PRODUCT PAGE
    final String PRODUCT_PAGE_CONTAINERR_CLASS = "product-essential";
    // MODAL
    final String MODAL_CLOSE_CLASS = "close-reveal-modal";
    // CART
    final String SHOPPING_CART_EMPTY = "cart-empty";
    final String TR_LIST_EVEN_PRODUCT_CLASS = "even";

}
