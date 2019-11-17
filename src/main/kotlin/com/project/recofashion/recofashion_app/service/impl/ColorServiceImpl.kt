package com.project.recofashion.recofashion_app.service.impl

import com.project.recofashion.recofashion_app.entity.user.Color
import com.project.recofashion.recofashion_app.service.ColorService
import org.springframework.stereotype.Service

@Service
class ColorServiceImpl : ColorService {
    override fun getTonInTon(color: Color): List<Color> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTonOnTon(color: Color): List<Color> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*주어진 점으로부터 x=y=z 직선에 가장 가까운 점을 구한다.*/
    override fun getMonoColor(color: Color): Color {

    }

    /*주어진 점의 원색을 구한다.*/
    override fun getVividColor(color: Color): Color {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*1. 주어진 점과 점(255,255,255)를 지나는 직선을 구한다.
    * 2. 이 직선이 rgb boundary와 접하는 점을 구한다.
    * 3. 이 점과 점(255,255,255)를 1:2로 내분하는 점을 찾는다.
    * */
    override fun getPastelColor(color: Color): Color {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*1. 주어진 점과 점(0, 0, 0)를 지나는 직선을 구한다.
    * 2. 이 직선이 rgb boundary와 접하는 점을 구한다.
    * 3. 이 점과 점(0, 0, 0)를 1:2로 내분하는 점을 찾는다.
    * */
    override fun getDeepColor(color: Color): Color {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}