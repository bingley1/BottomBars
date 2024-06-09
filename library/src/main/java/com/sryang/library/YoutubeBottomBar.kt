import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sryang.library.R

@Preview
@Composable
fun YoutubeBottomBar() {
    var currentTab by remember { mutableStateOf("home") }

    BottomAppBar(modifier = Modifier.height(65.dp)) {
        NavigationBar(
        ) {
            NavigationBarItem(selected = currentTab == "home", onClick = {
                currentTab = "home"
            }, icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = if (currentTab == "home") painterResource(id = R.drawable.ic_sel_home) else painterResource(
                            id = R.drawable.ic_home
                        ),
                        contentDescription = ""
                    )
                    Text(text = "home", fontSize = 10.sp)
                }
            })

            NavigationBarItem(selected = currentTab == "shorts", onClick = {
                currentTab = "shorts"
            }, icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = if (currentTab == "shorts") painterResource(id = R.drawable.ic_sel_shorts) else painterResource(
                            id = R.drawable.ic_shorts
                        ),
                        contentDescription = "",
                        Modifier.size(35.dp)
                    )
                    Text(text = "shorts", fontSize = 10.sp)
                }

            })

            NavigationBarItem(selected = false, onClick = {
            }, icon = {
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)
                )
            })

            NavigationBarItem(
                selected = currentTab == "subscriptions",
                onClick = {
                    currentTab = "subscriptions"
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = if (currentTab == "subscriptions") painterResource(id = R.drawable.ic_sel_sub) else painterResource(
                                id = R.drawable.ic_sub
                            ),
                            contentDescription = ""
                        )
                        Text(text = "subscriptions", fontSize = 10.sp)
                    }
                })

            NavigationBarItem(selected = currentTab == "you", onClick = {
                currentTab = "you"
            }, icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.ic_youtube3),
                        contentDescription = "",
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .border(
                                if (currentTab == "you") 2.dp else 0.dp, Color.LightGray, CircleShape
                            )
                    )
                    Text(text = "you", fontSize = 10.sp)
                }
            }
            )
        }
    }
}