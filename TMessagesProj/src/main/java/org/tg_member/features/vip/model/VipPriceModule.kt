package org.tg_member.features.vip.model

class VipPriceModule {

    var vipCount: String? = null
    var discount: String? = null
    var price: String? = null

    constructor()

    constructor(vipCount: String?, discount: String?, price: String?) {
        this.vipCount = vipCount
        this.discount = discount
        this.price = price
    }

}