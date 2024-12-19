package org.tg_member.features.buy

import androidx.recyclerview.widget.LinearLayoutManager
import org.telegram.messenger.databinding.BuyFragmentBinding
import org.tg_member.features.buy.adapters.BuyAdapter
import org.tg_member.features.vip.model.VipDisplayData

class BuyFragment(var buyFragmentBinding: BuyFragmentBinding) {

    lateinit var buyAdapter: BuyAdapter
    lateinit var vipPriceList:ArrayList<VipDisplayData>

    fun createView() {

        setUpAdapter()

    }

    private fun setUpAdapter() {
        vipPriceList = ArrayList()
        vipPriceList.add(VipDisplayData(5000, 0, 150F))
        vipPriceList.add(VipDisplayData(1000, 20, 150F))
        vipPriceList.add(VipDisplayData(5000, 32, 150F))
        vipPriceList.add(VipDisplayData(10000, 15, 150F))
        buyFragmentBinding.rv.layoutManager = LinearLayoutManager(buyFragmentBinding.root.context)
        buyAdapter = BuyAdapter(vipPriceList)
        buyFragmentBinding.rv.adapter = buyAdapter
    }
}