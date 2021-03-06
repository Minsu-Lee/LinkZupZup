package com.depromeet.linkzupzup.view.scrap.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.linkzupzup.R
import com.depromeet.linkzupzup.base.BaseView
import com.depromeet.linkzupzup.presenter.ScrapDetailViewModel
import com.depromeet.linkzupzup.presenter.model.TagColor
import com.depromeet.linkzupzup.ui.theme.LinkZupZupTheme
import com.depromeet.linkzupzup.utils.CommonUtil
import com.depromeet.linkzupzup.utils.DLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ScrapDetailUI: BaseView<ScrapDetailViewModel>() {

    @Composable
    override fun onCreateViewContent() {
        LinkZupZupTheme {
            Surface(color = MaterialTheme.colors.background) {
                BodyContent(vm)
            }
        }
    }

}


@Composable
fun BodyContent(viewModel: ScrapDetailViewModel? = null) {
    val middleTopPadding = 20.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {


        // content
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Vertical
            )) {

            // top header
            Image(painter = painterResource(id = R.drawable.ic_scrap_detail_top_bg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth())

            // body
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                // .padding(horizontal = 24.dp)
                .absoluteOffset(y = -middleTopPadding)) {

                Box(Modifier.padding(horizontal = 24.dp)) {
                    Image(painter= painterResource(id = R.drawable.ic_scrap_profile_img),
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp))
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .absoluteOffset(y = middleTopPadding)) {

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 72.dp, end = 24.dp)
                        .height(44.dp)) {

                        Text("?????????",
                            color = Color.Gray,
                            style = TextStyle(fontFamily = FontFamily(Font(resId = R.font.spoqa_hansansneo_regular, weight = FontWeight.W400)), fontSize = 12.sp, lineHeight = 16.8.sp, color = Color(0x878D91)),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .background(Color.White)
                        )

                        Spacer(Modifier.weight(1f))

                        Box(modifier = Modifier
                            .size(44.dp)
                            .fillMaxHeight()
                            .clickable { DLog.e("Jackson", "more click") }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_gray_more),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(Color.White)
                                    .align(Alignment.Center)
                            )
                        }
                    }

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 48.dp, bottom = 16.dp)) {

                        Text("??????????????? ????????? ????????? ?????? DNA??? ????????? ?????????????",
                            style = TextStyle(fontFamily = FontFamily(Font(resId = R.font.spoqa_hansansneo_bold, weight = FontWeight.W700)), fontSize = 18.sp, lineHeight = 22.5.sp, color = Color(0xFF292A2B)),
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 24.dp))

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("IT ????????? ???????????? ????????? ????????? ????????? ?????? ?????? ????????? ????????? ???????????? ????????? ?????? ?????? ???????????? ???????????? ??????. ????????? ??????????????? ???????????? ?????? ????????????????????? ??? ???????????? ????????? ?????? ???...",
                            style = TextStyle(fontFamily = FontFamily(Font(resId = R.font.spoqa_hansansneo_medium, weight = FontWeight.W400)), fontSize = 12.sp, lineHeight = 16.8.sp, color = Color(0xFF878D91)),
                            maxLines = 3,
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 24.dp))

                        Spacer(modifier = Modifier.height(20.dp))

                        MultiLineTagList(viewModel?.getTagList() ?: arrayListOf(), contentPadding = PaddingValues(24.dp, 0.dp))

                        Spacer(Modifier.weight(1f))

                        Card(elevation = 0.dp, modifier = Modifier
                            .height(26.dp)
                            .padding(start = 24.dp, end = 24.dp)
                            .clickable { DLog.e("Jackson", "click link alram setting!") }) {
                            Column {
                                Spacer(Modifier.height(5.dp))
                                Row (
                                    Modifier
                                        .fillMaxWidth()
                                        .height(21.dp)
                                ) {

                                    Image(painter = painterResource(id = R.drawable.ic_link_alram_img),
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp))

                                    Spacer(Modifier.width(4.dp))

                                    Text("??? ????????? ?????? ????????? ???????????????!",
                                        style = TextStyle(fontFamily = FontFamily(Font(resId = R.font.spoqa_hansansneo_regular, weight = FontWeight.W400)), fontSize = 12.sp, lineHeight = 16.8.sp, color = Color(0xFF292A2B)))

                                }
                            }
                        }

                        Spacer(Modifier.height(20.dp))

                        Button(onClick = { DLog.e("Jackson", "click read button") },
                            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFF4076F6), contentColor = Color.White),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .padding(horizontal = 24.dp)) {
                            Text("?????? ??????!",
                                style = TextStyle(fontFamily = FontFamily(Font(resId = R.font.spoqa_hansansneo_medium, weight = FontWeight.W700)), fontSize = 14.sp, lineHeight = 17.5.sp),
                                textAlign = TextAlign.Center)
                        }


                    }
                }
            }
        }

        // close
        Box(modifier= Modifier
            .size(64.dp)
            .padding(16.dp)
            .align(Alignment.TopEnd)
            .clickable { DLog.e("Jackson", "Click close button") }) {

            Image(
                painter = painterResource(id = R.drawable.ic_gray_close),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
            )
        }
    }
}

@Composable
fun MultiLineTagList(tags: List<String>, horizontalItemLimit: Int = 10, backgroundColor: Color = Color(0xFFAAAAAA), textColor: Color = Color(0xFFAAAAAA), contentPadding: PaddingValues = PaddingValues(0.dp)) {

    val colors = remember { mutableStateOf(arrayListOf<TagColor>().apply {
        tags.forEach { TagColor(backgroundColor, textColor) }
    }) }

    val firstLineLen = remember {
        mutableStateOf(
            if (tags.size <= horizontalItemLimit) tags.size
            else horizontalItemLimit)
    }

    val secondLineLen = remember {
        mutableStateOf(
            if (horizontalItemLimit < tags.size && tags.size >= horizontalItemLimit * 2) horizontalItemLimit * 2
            else tags.size)
    }

    Column {

        CoroutineScope(Dispatchers.Main).launch {
            arrayListOf<TagColor>().apply {
                tags.forEach { add(CommonUtil.getRandomeTagColor()) }
            }.let { colors.value = it }
        }

        TagList(tags.subList(0, firstLineLen.value), colors, contentPadding)
        if (secondLineLen.value > 0) {
            Spacer(Modifier.height(12.dp))
            TagList(tags.subList(horizontalItemLimit, secondLineLen.value), colors, contentPadding)
        }
    }
}


@Composable
fun TagList(tags: List<String>, colors: MutableState<ArrayList<TagColor>>, contentPadding: PaddingValues = PaddingValues(0.dp)) {
    if (colors.value.size > 0) LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = contentPadding) {
        itemsIndexed(tags) { idx, tag ->
            colors.value[idx].let { tagColor ->
                TagView(idx, tag, tagColor.bgColor, tagColor.textColor)
            }
        }
    }
}

@Composable
fun TagView(idx: Int, tagStr: String, backgroundColor: Color = Color(0xFFAAAAAA), textColor: Color = Color(0xFFAAAAAA)) {

    val colors = remember { mutableStateOf(TagColor(backgroundColor, textColor)) }
    Card(Modifier.clickable { DLog.e("Jackson", "idx: $idx, click, Tag: $tagStr")  },
        shape = RoundedCornerShape(2.dp),
        elevation = 0.dp,
        backgroundColor = colors.value.bgColor) {
        Column(modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)) {
            Text(tagStr,
                style = TextStyle(fontFamily = FontFamily(Font(resId = R.font.spoqa_hansansneo_medium, weight = FontWeight.W400)), fontSize = 10.sp, lineHeight = 2.sp, color = Color(0xFFE88484)),
                color = colors.value.textColor,
                modifier = Modifier
                    .height(12.dp)
                    .absolutePadding(0.dp, 0.dp, 0.dp, 0.dp), maxLines = 1)

        }
    }
}

@Preview
@Composable
fun previewSample() {
    LinkZupZupTheme {
        Surface(color = MaterialTheme.colors.background) {
            BodyContent()
        }
    }
}