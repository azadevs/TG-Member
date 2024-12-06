package org.tg_member.features.buy

import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.databinding.BuyFragmentBinding
import org.tg_member.features.buy.adapters.BuyAdapter
import org.tg_member.features.vip.model.VipPriceModule

class BuyFragment(var buyFragmentBinding: BuyFragmentBinding) {

    lateinit var buyAdapter: BuyAdapter
    lateinit var vipPriceList:ArrayList<VipPriceModule>

    fun createView() {

        setUpAdapter()

    }

    private fun setUpAdapter() {
        vipPriceList = ArrayList()
        vipPriceList.add(VipPriceModule("5000", "5%", "100000 so'm"))
        vipPriceList.add(VipPriceModule("10000", "10%", "150000 so'm"))
        vipPriceList.add(VipPriceModule("15000", "15%", "200000 so'm"))
        vipPriceList.add(VipPriceModule("20000", "20%", "225000 so'm"))
        vipPriceList.add(VipPriceModule("25000", "25%", "250000 so'm"))
        buyFragmentBinding.rv.layoutManager = LinearLayoutManager(buyFragmentBinding.root.context)
        buyAdapter = BuyAdapter(vipPriceList)
        buyFragmentBinding.rv.adapter = buyAdapter
    }
}