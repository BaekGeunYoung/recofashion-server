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
        /*우리가 원하는 점을 벡터 V라 하고, 주어진 점(a,b,c)을 벡터 w라 하면, (v-w)와 (1,1,1)을 내적한 값이 0이 되어야 한다.
        * 이 조건을 만족하는 벡터 v의 x,y,z 값은 (a+b+c)/3이다. */
        val monoValue = (color.r + color.g + color.b) / 3
        return Color(monoValue, monoValue, monoValue)
    }

    /*주어진 점의 원색을 구한다.
    * 주어진 점에서 rgb boundary 정육면체의 6개 모서리 중 가장 가까운 모서리에 내린 수선의 발을 구한다.
    * */
    override fun getVividColor(color: Color): Color {
        val x = color.r
        val y = color.g
        val z = color.b

        return if(x < y) {
            if(255 - y < x) {
                if(x > z) greenYellowPerpendicular(color)
                else greenSkyPerpendicular(color)
            } else {
                if(z < y) greenSkyPerpendicular(color)
                else blueSkyPerpendicular(color)
            }
        }
        else {
            if(255 - x > y) {
                if(z > x) bluePurplePerpendicular(color)
                else redPurplePerpendicular(color)
            } else {
                if(y < z) redPurplePerpendicular(color)
                else redYellowPerpendicular(color)
            }
        }
    }

    fun redPurplePerpendicular(color: Color) : Color = Color(255, 0, color.b)

    fun redYellowPerpendicular(color: Color): Color = Color(255, color.g, 0)

    fun greenYellowPerpendicular(color: Color): Color = Color(color.r, 255, 0)

    fun greenSkyPerpendicular(color: Color): Color = Color(0, 255, color.b)

    fun blueSkyPerpendicular(color: Color): Color = Color(0, color.g, 255)

    fun bluePurplePerpendicular(color: Color): Color = Color(color.r, 0, 255)

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