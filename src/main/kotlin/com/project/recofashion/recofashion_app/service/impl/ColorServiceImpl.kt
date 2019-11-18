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

    /*색깔을 빨강색-보라색 계열 원색으로 변환*/
    fun redPurplePerpendicular(color: Color) : Color = Color(255, 0, color.b)

    /*색깔을 빨강색-노란색 계열 원색으로 변환*/
    fun redYellowPerpendicular(color: Color): Color = Color(255, color.g, 0)

    /*색깔을 초록색-노란색 계열 원색으로 변환*/
    fun greenYellowPerpendicular(color: Color): Color = Color(color.r, 255, 0)

    /*색깔을 초록색-하늘색 계열 원색으로 변환*/
    fun greenSkyPerpendicular(color: Color): Color = Color(0, 255, color.b)

    /*색깔을 파란색-하늘색 계열 원색으로 변환*/
    fun blueSkyPerpendicular(color: Color): Color = Color(0, color.g, 255)

    /*색깔을 파란색-보라색 계열 원색으로 변환*/
    fun bluePurplePerpendicular(color: Color): Color = Color(color.r, 0, 255)

    /*1. 주어진 색깔의 원색을 구한다.
    * 2. 구한 원색에 대응하는 점과 점(255, 255, 255)를 3:1으로 내분하는 점을 구한다.
    * */
    override fun getPastelColor(color: Color): Color {
        //비비드 톤 컬러를 구할 때 작성했던 함수 활용
        val vividColor = getVividColor(color)
        return internalDivisionPoint(1, 3, vividColor, Color(255, 255, 255))
    }

    /*1. 주어진 색깔의 원색을 구한다.
    * 2. 구한 원색에 대응하는 점과 점(0, 0, 0)을 3:1로 내분하는 점을 구한다.
    * */
    override fun getDeepColor(color: Color): Color {
        //비비드 톤 컬러를 구할 때 작성했던 함수 활용
        val vividColor = getVividColor(color)
        return internalDivisionPoint(1, 3, vividColor, Color(0, 0, 0))
    }

    /*point1, point2를 ratio1 : ratio2로 내분하는 점을 구한다.*/
    fun internalDivisionPoint(ratio1: Int, ratio2: Int, point1: Color, point2: Color): Color {
        val x = (ratio2 * point1.r + ratio1 * point2.r) / (ratio1 + ratio2)
        val y = (ratio2 * point1.g + ratio1 * point2.g) / (ratio1 + ratio2)
        val z = (ratio2 * point1.b + ratio1 * point2.b) / (ratio1 + ratio2)

        return Color(x, y, z)
    }
}