package ru.tander.parsejson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ru.tander.parsejson.entities.ContentImage;
import ru.tander.parsejson.entities.ContentVideo;
import ru.tander.parsejson.entities.Json;
import ru.tander.parsejson.entities.News;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest<T> {
    private Gson gson = new Gson();


    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void PrintNewsTest(){
        for(News n:getNewsList()){
            for(Json json:n.getJson()){
                if(json.getContentVideo()!=null) {
                    ContentVideo contentImage = (ContentVideo) json.getContentVideo().get(0);
                    System.out.println(contentImage.getSource() + " url " + contentImage.getUrl() + " poster " + contentImage.getPoster());
                    System.out.println();
                }
            }
        }
    }

    public List<News> getNewsList(){
        List<News>newsList = new ArrayList<>();
        JsonArray array = new JsonParser().parse(parseJson).getAsJsonArray();
        for (int i = 0; i < array.size(); i++) {
            News news = gson.fromJson(array.get(i), News.class);
            JsonArray jsonArray = array.get(i).getAsJsonObject().get("json").getAsJsonArray();

            for (int j = 0; j < jsonArray.size(); j++) {
                Json oneJson = news.getJson().get(j);
                String type = jsonArray.get(j).getAsJsonObject().get("type").getAsString();
                JsonElement contentElement = jsonArray.get(j).getAsJsonObject().get("content");
                addContent(type, contentElement, oneJson);
//                T content = addContent(type, contentElement);
//                oneJson.setContent(content);
            }
            newsList.add(news);
        }
        return newsList;
    }

    private void addContent(String type, JsonElement contentObject, Json oneJson){
        switch (type) {
            case "text":
                String content = contentObject.getAsString();
                oneJson.addContentText(content);
                break;
            case "image":
                List<ContentImage> contentImageList = new Gson().fromJson(
                        contentObject, new TypeToken<List<ContentImage>>() {}.getType());
                for(ContentImage image:contentImageList){
                    oneJson.addContentImage(image);
                }
                break;
            case "video":
                List<ContentVideo> contentVideoList = new Gson().fromJson(
                        contentObject, new TypeToken<List<ContentVideo>>() {}.getType());
                for(ContentVideo video:contentVideoList){
                    oneJson.addContentVideo(video);
                }
                break;
            default:
        }
    }

    String parseJson = "[\n" +
            "    {\n" +
            "        \"id\": 202,\n" +
            "        \"text_id\": \"2015/11/26/158\",\n" +
            "        \"date\": \"2017-04-11 03:21:06\",\n" +
            "        \"last_update\": \"2016-02-29 08:38:46\",\n" +
            "        \"version\": 11,\n" +
            "        \"title\": \"Esse ullamco id dolor quis enim tempor minim eu et.\",\n" +
            "        \"rubric\": \"quis\",\n" +
            "        \"type\": \"ipsum\",\n" +
            "        \"spb\": 1,\n" +
            "        \"json\": [\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Non eu exercitation sit sint consectetur eu dolore. Aliquip quis amet nostrud mollit anim officia ullamco elit minim mollit minim proident. Labore ex qui exercitation est fugiat officia aliqua ut irure. Laboris qui irure duis elit veniam cupidatat. Et dolore officia ipsum sit.\\r\\nCillum sit id proident sint nostrud reprehenderit dolore. Amet nulla exercitation irure sunt adipisicing magna proident officia proident excepteur sit adipisicing. Minim ut laboris in est consequat exercitation id officia ex. Anim elit sint sunt consequat sunt esse. Veniam mollit ullamco reprehenderit amet. Consectetur culpa labore minim duis culpa cillum. Id sint sint occaecat velit ipsum enim labore magna labore et laborum minim consequat.\\r\\nId ex qui minim nisi ad sit eiusmod. Nulla culpa non fugiat fugiat voluptate id ipsum occaecat nulla in eu nisi consectetur voluptate. Cillum magna do dolor ad id voluptate irure sunt est fugiat proident. Sint mollit in ullamco quis aliqua irure commodo pariatur anim minim.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"image\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://placehold.it/64x64\",\n" +
            "                        \"source\": \"culpa\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Consequat dolore irure sint consectetur sint ad ut officia. Officia tempor velit pariatur irure officia ut commodo occaecat anim consectetur eiusmod dolor exercitation ut. Ullamco consequat excepteur pariatur excepteur irure aliquip ipsum ex sunt reprehenderit anim anim enim. In anim eiusmod consequat ea consequat qui do tempor. Sit irure dolor labore minim culpa sunt commodo veniam eiusmod est ad reprehenderit proident elit.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"video\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://www.example.com/nisi.mp4\",\n" +
            "                        \"source\": \"nisi\",\n" +
            "                        \"poster\": \"http://placehold.it/140x100\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Aliquip ea consequat fugiat laboris anim laborum officia ea ut anim proident. Ipsum adipisicing et excepteur culpa ex. Pariatur velit ipsum mollit ullamco dolore officia labore do.\\r\\nAliquip mollit laboris adipisicing magna laboris dolore pariatur laborum excepteur cillum. Tempor sint reprehenderit labore laborum consequat ullamco veniam ipsum nostrud eu enim ea. Ex sint enim non mollit amet id esse incididunt culpa consequat ad ut. Fugiat qui exercitation pariatur irure est ullamco aute nostrud magna mollit cupidatat minim. Enim laboris voluptate culpa irure elit cillum anim aliquip et irure. Laborum excepteur adipisicing velit laboris excepteur et enim non veniam amet proident.\\r\\nEiusmod voluptate tempor consequat anim ex non ullamco amet duis exercitation nostrud. Sunt anim ut nisi eiusmod occaecat id magna aute cillum dolore. Nisi reprehenderit Lorem aute deserunt elit est magna. Aute ex sint est ut est laborum in aliqua dolore voluptate ea tempor duis. Amet eu elit commodo id culpa. Nulla cillum pariatur consequat eiusmod anim id duis fugiat duis qui minim.\\r\\n\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"link\": \"http://www.example.com/est.htm\",\n" +
            "        \"forum_id\": 150398,\n" +
            "        \"lead\": \"Deserunt labore amet commodo eiusmod laboris excepteur ea proident irure cupidatat occaecat. Id nostrud ad laboris voluptate ullamco eiusmod elit elit amet ipsum ea. Ipsum tempor magna enim quis exercitation non incididunt. Excepteur ullamco pariatur tempor ea non nostrud.\\r\\n\",\n" +
            "        \"image_url\": \"http://placehold.it/237x105\",\n" +
            "        \"image_source\": \"deserunt cupidatat\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 130,\n" +
            "        \"text_id\": \"2015/12/01/211\",\n" +
            "        \"date\": \"2015-09-19 01:46:43\",\n" +
            "        \"last_update\": \"2016-11-10 12:07:25\",\n" +
            "        \"version\": 13,\n" +
            "        \"title\": \"У этого элемента нет картинки\",\n" +
            "        \"rubric\": \"sunt\",\n" +
            "        \"type\": \"quis\",\n" +
            "        \"spb\": 0,\n" +
            "        \"json\": [\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Proident et dolore esse reprehenderit qui anim. Et enim fugiat aliqua proident qui anim cillum velit reprehenderit occaecat non quis aute in. Fugiat dolor fugiat et ut est qui exercitation magna. In pariatur occaecat qui non ipsum nisi ex Lorem. Minim culpa eiusmod reprehenderit commodo sint do dolor eiusmod aute nostrud velit.\\r\\nMinim consequat Lorem ut ullamco labore occaecat veniam cupidatat ad nostrud aliqua sit sint adipisicing. Proident culpa excepteur cupidatat veniam sunt ex consectetur tempor. Do laboris eiusmod duis commodo nisi commodo laboris anim cupidatat veniam in irure incididunt tempor.\\r\\nIncididunt cillum cupidatat est nulla labore aliqua cillum duis officia sunt eiusmod laboris nisi nostrud. Reprehenderit elit aliquip do fugiat elit eiusmod aliquip anim non cupidatat dolor nostrud voluptate enim. Esse et aliquip ea commodo do enim officia.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"image\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://placehold.it/64x64\",\n" +
            "                        \"source\": \"id\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Et commodo eu anim ullamco dolore culpa non dolor sunt in deserunt deserunt. Culpa ex sunt nulla voluptate esse qui commodo consequat officia velit. Amet duis amet nisi ipsum minim magna nisi mollit amet minim aliqua incididunt cupidatat. Do dolor velit do fugiat non eiusmod ut dolor adipisicing dolore. Qui eu excepteur commodo eu aliqua est consectetur.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"video\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://www.example.com/eiusmod.mp4\",\n" +
            "                        \"source\": \"non\",\n" +
            "                        \"poster\": \"http://placehold.it/140x100\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Cillum adipisicing dolor deserunt reprehenderit sint eiusmod cillum esse. Aliqua ullamco laboris nulla deserunt excepteur cupidatat culpa ea laboris non occaecat sint nisi exercitation. Deserunt exercitation laboris ullamco voluptate eiusmod labore.\\r\\nQui culpa labore ad aute mollit eiusmod mollit. Aute labore incididunt fugiat nisi laborum cillum exercitation consectetur velit dolor deserunt laboris. Culpa commodo et incididunt incididunt reprehenderit voluptate incididunt tempor. Eiusmod et cupidatat excepteur quis. Magna sit dolor commodo ullamco culpa est do et dolore. Dolor voluptate magna eiusmod excepteur incididunt. In aliquip excepteur incididunt fugiat dolor tempor consequat nulla et veniam culpa ex.\\r\\nSit consectetur nulla duis anim Lorem laborum ipsum. Do sint ex laborum dolor ut sint officia commodo Lorem. Magna ut laborum qui id pariatur minim veniam reprehenderit exercitation consequat. Pariatur eiusmod ipsum sunt cupidatat non dolore ex nisi nulla commodo consequat dolore cupidatat do. Officia officia ullamco incididunt id. Velit est ad tempor Lorem adipisicing.\\r\\n\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"link\": \"http://www.example.com/laborum.htm\",\n" +
            "        \"forum_id\": 234193,\n" +
            "        \"lead\": \"Do laboris ut commodo cillum mollit deserunt Lorem reprehenderit minim ipsum. Ut ullamco culpa laboris esse qui ea nulla anim ea Lorem consectetur ad non. Elit ut minim et quis quis consequat. Proident minim aliquip cupidatat dolor irure quis consequat culpa occaecat ea occaecat officia anim anim.\\r\\n\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 180,\n" +
            "        \"text_id\": \"2016/10/04/127\",\n" +
            "        \"date\": \"2015-11-16 12:20:00\",\n" +
            "        \"last_update\": \"2015-04-24 04:00:16\",\n" +
            "        \"version\": 23,\n" +
            "        \"title\": \"Adipisicing enim minim Lorem voluptate tempor quis fugiat pariatur aliquip eiusmod.\",\n" +
            "        \"rubric\": \"velit\",\n" +
            "        \"type\": \"anim\",\n" +
            "        \"spb\": 0,\n" +
            "        \"json\": [\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Laboris labore amet nisi officia Lorem eu ullamco dolor. Minim non ad veniam laborum labore cupidatat irure magna ex sit. Aliqua cupidatat et mollit nulla pariatur dolore id sunt consectetur reprehenderit laboris veniam est.\\r\\nConsequat sunt aute esse duis voluptate duis est mollit fugiat proident sint ea. Dolore non commodo cillum consectetur fugiat commodo duis cupidatat esse qui exercitation esse. Laborum aute quis do ad ea Lorem occaecat ipsum velit amet adipisicing proident nostrud laboris. Labore ipsum aute amet in excepteur aliquip elit nisi fugiat in cupidatat. Enim tempor sit occaecat exercitation dolore eu eiusmod laboris aliqua dolor esse laboris.\\r\\nExercitation sint aliqua ut laboris ea. Enim nisi nulla pariatur pariatur pariatur sint nulla sint incididunt. Consequat occaecat aliqua pariatur sunt dolor incididunt pariatur velit adipisicing. Laborum magna proident nisi laboris aute labore. Qui dolore do esse elit cupidatat proident dolore excepteur commodo. Excepteur magna mollit eu sint ex voluptate quis incididunt cillum pariatur duis aute aliquip quis. Veniam occaecat elit ad minim officia adipisicing nostrud veniam ipsum eiusmod quis aliquip.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"image\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://placehold.it/64x64\",\n" +
            "                        \"source\": \"reprehenderit\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Exercitation est ea excepteur duis ullamco veniam. Quis duis nostrud culpa sunt. Esse non cupidatat consectetur non ut veniam ex aliquip minim ea id voluptate officia. Consequat exercitation ipsum occaecat elit reprehenderit ad sunt pariatur aliqua do irure proident. Nostrud qui deserunt voluptate pariatur aliquip mollit esse sit dolore sint dolore enim minim. Ut elit esse id ullamco tempor voluptate dolore eu fugiat consectetur nulla tempor.\\r\\nDuis voluptate ipsum qui duis irure aliquip do tempor. Labore sint magna enim elit nostrud et anim voluptate proident. Laborum duis deserunt cupidatat ad adipisicing commodo voluptate minim reprehenderit. Incididunt duis laboris laborum est minim deserunt pariatur deserunt incididunt ut duis. Velit quis sit ullamco ad non cillum magna sit id. Voluptate elit consectetur officia in ut eiusmod aliquip exercitation tempor dolore. Excepteur quis officia nostrud deserunt commodo proident culpa labore sint aliquip proident fugiat excepteur non.\\r\\nQuis nisi adipisicing amet nulla nostrud laborum duis. Cupidatat officia deserunt nulla veniam est sint minim tempor exercitation eu fugiat quis amet. Mollit in ut deserunt velit mollit quis. Sunt eiusmod veniam labore nisi culpa veniam cillum incididunt commodo non sunt aliquip. Dolore ullamco aute enim aute excepteur et ut in culpa velit exercitation enim irure excepteur. Eu irure culpa dolore voluptate velit voluptate quis anim.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"video\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://www.example.com/ex.mp4\",\n" +
            "                        \"source\": \"aute\",\n" +
            "                        \"poster\": \"http://placehold.it/140x100\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Quis dolor sint aliquip dolore voluptate excepteur consectetur mollit proident. Sit mollit fugiat reprehenderit esse aute do aliquip officia consectetur duis aliquip cillum est. Sit quis nisi sint reprehenderit et minim et Lorem in ipsum eiusmod in. Eu dolor et duis magna amet id sit eiusmod eu Lorem aute.\\r\\n\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"link\": \"http://www.example.com/est.htm\",\n" +
            "        \"forum_id\": 6192,\n" +
            "        \"lead\": \"Dolore adipisicing adipisicing enim consectetur aute occaecat mollit do eu. Quis aliquip commodo enim et laboris incididunt consectetur officia do et qui. Laborum do do consequat deserunt nulla duis esse labore. Fugiat reprehenderit dolore irure voluptate consectetur aliqua labore nostrud labore Lorem exercitation ut elit.\\r\\n\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 284,\n" +
            "        \"text_id\": \"2015/01/31/181\",\n" +
            "        \"date\": \"2016-12-24 08:17:57\",\n" +
            "        \"last_update\": \"2016-01-01 08:15:56\",\n" +
            "        \"version\": 3,\n" +
            "        \"title\": \"Ad magna eiusmod enim laborum ad ipsum proident velit mollit ad.\",\n" +
            "        \"rubric\": \"aute\",\n" +
            "        \"type\": \"tempor\",\n" +
            "        \"spb\": 1,\n" +
            "        \"json\": [\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Mollit occaecat non sit veniam id amet sit eiusmod cillum. Esse in laborum occaecat ut pariatur occaecat laborum irure ad cillum consequat. Culpa ad officia sint adipisicing. Et nisi sunt aliqua nostrud eu esse esse duis nostrud mollit. Eu eiusmod id sint et pariatur minim qui fugiat exercitation et.\\r\\nUt Lorem occaecat laboris ad. Sunt sit est officia laborum et voluptate velit est aliquip enim consequat. Tempor eiusmod duis exercitation proident incididunt aliquip veniam aliqua deserunt velit qui labore. Minim velit irure nulla et tempor non sint. Cupidatat dolor aliquip laboris fugiat adipisicing ipsum et dolor eu quis officia aliquip irure voluptate. Ullamco ea pariatur Lorem reprehenderit voluptate aute cillum nisi commodo ut reprehenderit exercitation. Laboris consectetur labore do excepteur nulla laboris deserunt irure dolor consequat ipsum veniam ea aute.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"image\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://placehold.it/64x64\",\n" +
            "                        \"source\": \"non\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"In cupidatat ipsum minim ullamco ad officia. Ea qui cillum ex eiusmod mollit aute exercitation ipsum anim. Anim nulla ad eu pariatur tempor laborum id adipisicing nisi eu est. Velit tempor incididunt culpa enim minim duis proident dolore cupidatat officia ullamco. Excepteur minim Lorem esse proident anim sunt labore. Nostrud consectetur cupidatat officia ex qui. Dolor voluptate culpa proident non Lorem do nulla labore duis.\\r\\nMinim amet nisi reprehenderit deserunt amet reprehenderit deserunt cupidatat mollit. Labore labore culpa fugiat aliquip Lorem adipisicing sint do. Eu ut sint eu ad est culpa minim magna ea non. Nulla non do labore consectetur magna adipisicing do occaecat irure laborum aute. Tempor tempor amet reprehenderit in adipisicing. Tempor do amet non aute incididunt. Labore ea exercitation labore eu.\\r\\nEt incididunt sit labore aliqua enim. Ex qui sunt nostrud tempor. Pariatur occaecat in dolore duis sunt tempor elit tempor id ullamco voluptate ullamco incididunt cupidatat. Incididunt tempor mollit quis cillum ullamco reprehenderit nisi eu. Enim eiusmod aliquip veniam eiusmod incididunt duis.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"video\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://www.example.com/incididunt.mp4\",\n" +
            "                        \"source\": \"reprehenderit\",\n" +
            "                        \"poster\": \"http://placehold.it/140x100\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Adipisicing cupidatat irure incididunt in occaecat pariatur sint in veniam. Deserunt tempor excepteur nisi irure pariatur aute non. Occaecat do ex non sit. Deserunt id nostrud quis deserunt nulla do reprehenderit magna deserunt non est reprehenderit exercitation. Nisi aliquip consectetur in ipsum velit pariatur cupidatat laborum nostrud eiusmod consectetur veniam. Ad enim pariatur ullamco occaecat adipisicing. Amet eu laboris enim sit proident amet excepteur amet incididunt.\\r\\n\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"link\": \"http://www.example.com/magna.htm\",\n" +
            "        \"forum_id\": 195591,\n" +
            "        \"lead\": \"Labore reprehenderit labore duis ipsum aliqua excepteur voluptate mollit. Aliquip Lorem duis cupidatat laborum fugiat sunt sit et enim laborum cupidatat nisi consectetur. Aliquip aliqua ut dolore pariatur elit anim est ut.\\r\\n\",\n" +
            "        \"image_url\": \"http://placehold.it/225x126\",\n" +
            "        \"image_source\": \"anim mollit aliqua sunt\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 8,\n" +
            "        \"text_id\": \"2015/08/16/197\",\n" +
            "        \"date\": \"2017-01-12 10:26:05\",\n" +
            "        \"last_update\": \"2015-04-23 04:32:36\",\n" +
            "        \"version\": 9,\n" +
            "        \"title\": \"Commodo ad consequat duis pariatur reprehenderit cupidatat consequat dolore.\",\n" +
            "        \"rubric\": \"nostrud\",\n" +
            "        \"type\": \"laborum\",\n" +
            "        \"spb\": 1,\n" +
            "        \"json\": [\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Velit culpa cupidatat et dolor aliquip ullamco non ad pariatur irure consectetur elit. Labore consequat incididunt qui ipsum cillum reprehenderit incididunt ea veniam et sint ad. Amet consequat ullamco culpa cupidatat et mollit fugiat. Occaecat nulla occaecat enim voluptate qui. Eiusmod aliquip pariatur laboris magna dolor ipsum veniam amet nisi aliquip.\\r\\nId excepteur pariatur excepteur ad fugiat. Minim cillum sint reprehenderit duis id ullamco. Eiusmod in nostrud incididunt eiusmod mollit. Non et sint fugiat nulla ut dolor duis dolore commodo officia. Eu sint quis sit do commodo non in ullamco duis voluptate reprehenderit.\\r\\nExcepteur minim elit deserunt magna esse eiusmod eiusmod. Incididunt elit dolor ipsum fugiat ex deserunt reprehenderit magna cillum cillum cillum amet deserunt. Consequat voluptate laboris duis aliqua aliquip est minim adipisicing dolore mollit nisi exercitation qui proident.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"image\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://placehold.it/64x64\",\n" +
            "                        \"source\": \"duis\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Fugiat mollit officia elit ea aliqua minim id anim. Aliqua labore culpa occaecat duis proident proident nulla. Deserunt est deserunt quis sit in nulla do consectetur cupidatat elit ea pariatur eiusmod. Reprehenderit reprehenderit tempor esse eiusmod dolor cupidatat. Non proident aute reprehenderit minim eiusmod commodo culpa consectetur est id adipisicing.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"video\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://www.example.com/amet.mp4\",\n" +
            "                        \"source\": \"duis\",\n" +
            "                        \"poster\": \"http://placehold.it/140x100\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Commodo do ullamco deserunt consectetur ea culpa. Fugiat proident cupidatat mollit non incididunt aute voluptate tempor est deserunt ipsum. Non adipisicing eu qui sint minim est sint ad duis esse dolore ea id reprehenderit. Pariatur duis anim consequat aliquip fugiat eu labore magna est fugiat amet velit.\\r\\nCupidatat pariatur pariatur veniam culpa voluptate eiusmod eiusmod labore. Culpa ea ex officia et nulla labore ipsum voluptate mollit labore ut. Cillum cillum cillum culpa nostrud ad. Cillum sint adipisicing minim ipsum dolor aliquip laboris voluptate.\\r\\n\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"link\": \"http://www.example.com/irure.htm\",\n" +
            "        \"forum_id\": 8239,\n" +
            "        \"lead\": \"Minim sunt reprehenderit incididunt Lorem exercitation deserunt duis eu sint labore ea occaecat eu. Consequat qui dolore magna do ex non fugiat quis reprehenderit ex. Ut ad elit occaecat ullamco.\\r\\n\",\n" +
            "        \"image_url\": \"http://placehold.it/243x114\",\n" +
            "        \"image_source\": \"adipisicing cupidatat id officia adipisicing\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 283,\n" +
            "        \"text_id\": \"2015/02/26/145\",\n" +
            "        \"date\": \"2016-08-08 11:53:38\",\n" +
            "        \"last_update\": \"2017-02-24 04:10:31\",\n" +
            "        \"version\": 12,\n" +
            "        \"title\": \"Elit minim voluptate sit sunt.\",\n" +
            "        \"rubric\": \"cupidatat\",\n" +
            "        \"type\": \"aliqua\",\n" +
            "        \"spb\": 1,\n" +
            "        \"json\": [\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Cillum pariatur nisi id amet aute proident. Dolor ullamco ad voluptate voluptate enim esse sint mollit non. Ea aute non dolor minim elit est laborum nulla ad consequat ullamco aliqua exercitation. Consequat fugiat officia nostrud sint ea consectetur aute incididunt nulla commodo nisi. Id nisi enim amet cillum enim. Consequat dolor ut fugiat sunt velit do est aliquip ad.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"image\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://placehold.it/64x64\",\n" +
            "                        \"source\": \"sint\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Dolor duis consectetur ea id sit incididunt labore officia irure. Do id duis nostrud nostrud. Esse dolor in ut exercitation duis eiusmod aliqua nulla excepteur consectetur tempor nostrud velit deserunt. Mollit tempor irure et ex irure ex adipisicing officia elit non.\\r\\nOfficia elit amet enim aliqua excepteur tempor sint excepteur voluptate. Reprehenderit laboris quis id nostrud. Velit ullamco duis ad incididunt qui mollit nisi aliqua aliqua quis ipsum proident dolor. Dolore cillum nulla voluptate dolore reprehenderit occaecat eiusmod commodo reprehenderit deserunt qui ex sit. Est nisi esse Lorem velit Lorem aliquip minim. Nulla laboris labore minim tempor reprehenderit incididunt consectetur cillum adipisicing.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"video\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://www.example.com/adipisicing.mp4\",\n" +
            "                        \"source\": \"officia\",\n" +
            "                        \"poster\": \"http://placehold.it/140x100\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Dolor ea exercitation reprehenderit fugiat in officia aute minim irure. Cupidatat officia laborum dolor officia sint. Reprehenderit tempor voluptate occaecat Lorem laborum labore velit nisi esse irure. Adipisicing mollit anim cupidatat et enim incididunt consequat qui exercitation laborum cupidatat.\\r\\nVoluptate aute minim nisi ipsum. Irure eu ut sunt occaecat deserunt labore exercitation aute nisi ipsum in ipsum minim Lorem. Sit officia pariatur ex esse occaecat sunt. Ut laborum elit labore dolore cupidatat labore mollit irure sit est. Et ullamco commodo sunt occaecat Lorem anim laborum. Elit adipisicing et incididunt sit ipsum minim amet cupidatat eiusmod veniam minim est.\\r\\nDuis fugiat magna elit id sit Lorem dolore. Eiusmod excepteur sint qui nostrud cupidatat. Culpa sunt consectetur consequat Lorem voluptate ea dolore. Anim culpa quis labore magna aute cupidatat deserunt cillum aliquip id. Reprehenderit in incididunt officia et dolor Lorem incididunt sint voluptate sunt voluptate enim veniam. Non esse ex do nostrud proident id ipsum excepteur proident nostrud dolore minim.\\r\\n\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"link\": \"http://www.example.com/irure.htm\",\n" +
            "        \"forum_id\": 171951,\n" +
            "        \"lead\": \"Consectetur sit duis ut nulla dolor excepteur aliquip et. Laboris excepteur aute ex consectetur magna labore ut non qui adipisicing. Occaecat dolore sint est cupidatat consequat sit mollit enim deserunt ad. Do est irure ex reprehenderit culpa reprehenderit officia voluptate do consequat est laborum ullamco. Amet sit non adipisicing ut proident sit eu ut officia nostrud consequat ad. Magna nulla Lorem anim amet cillum irure voluptate Lorem magna enim duis occaecat. Lorem aliqua amet dolor do tempor incididunt laboris exercitation voluptate.\\r\\n\",\n" +
            "        \"image_url\": \"http://placehold.it/239x121\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 216,\n" +
            "        \"text_id\": \"2016/11/08/281\",\n" +
            "        \"date\": \"2016-07-11 04:22:58\",\n" +
            "        \"last_update\": \"2015-11-24 11:17:44\",\n" +
            "        \"version\": 16,\n" +
            "        \"title\": \"Occaecat dolor sint eiusmod ex elit minim consequat nisi.\",\n" +
            "        \"rubric\": \"amet\",\n" +
            "        \"type\": \"nostrud\",\n" +
            "        \"spb\": 1,\n" +
            "        \"json\": [\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Adipisicing fugiat ad incididunt non. Ut ex eiusmod nisi fugiat in. Duis labore magna laborum commodo anim. Voluptate aliquip Lorem magna incididunt ullamco quis duis ad sit ipsum amet sit nisi sunt. Ex ullamco ut adipisicing voluptate occaecat aliqua ipsum sint reprehenderit consectetur velit cupidatat ea et. Irure ex proident qui amet id id labore ad id laboris amet. Incididunt officia eiusmod commodo non excepteur enim.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"image\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://placehold.it/64x64\",\n" +
            "                        \"source\": \"aliqua\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Ea et pariatur ex enim do dolor et esse. Culpa nostrud exercitation irure cupidatat consequat nulla ex. Dolore sunt labore sint est enim reprehenderit aliqua labore veniam.\\r\\nSunt consectetur dolore sint occaecat excepteur dolor tempor. Officia consectetur nostrud exercitation exercitation incididunt consequat velit qui ipsum aliqua esse deserunt ea. Eu ea labore ipsum ex dolor proident eu ipsum deserunt in minim non. Tempor ipsum occaecat pariatur dolore dolore sit enim anim amet aliqua. Eu consectetur fugiat consectetur ex pariatur ad sit. Occaecat cupidatat reprehenderit dolor laboris consectetur. Aliquip tempor irure mollit aute sunt nisi Lorem labore officia aliquip elit ex qui nisi.\\r\\nExcepteur ex culpa minim nostrud elit deserunt eu. Tempor est proident cillum amet commodo id. Dolor deserunt id cillum quis. Enim qui id in culpa deserunt ullamco commodo ad labore nulla. Minim commodo enim laborum dolore consequat proident consectetur aliquip et aliqua cillum in mollit.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"video\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://www.example.com/occaecat.mp4\",\n" +
            "                        \"source\": \"in\",\n" +
            "                        \"poster\": \"http://placehold.it/140x100\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Qui incididunt amet sint sunt sunt do irure aute sit. Exercitation sunt nulla aute duis voluptate sit non aliquip ea eu. Dolor mollit ipsum pariatur et. Cupidatat culpa reprehenderit dolore ut nulla magna cillum eu cillum ex.\\r\\nDuis in labore labore magna fugiat ad reprehenderit occaecat enim aliquip. Nostrud est do et Lorem. Ullamco magna et sunt minim do aute id ad ea. Consequat id et consequat commodo.\\r\\nFugiat do laborum ea do Lorem dolor esse ut enim nulla sint labore cillum. Ut sint ut minim nulla ad ullamco quis nostrud voluptate. Id do elit ipsum voluptate amet do ullamco ex mollit.\\r\\n\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"link\": \"http://www.example.com/qui.htm\",\n" +
            "        \"forum_id\": 184576,\n" +
            "        \"lead\": \"Aliqua Lorem commodo commodo labore occaecat Lorem. Aute aute veniam sint laboris aliquip culpa veniam id proident elit veniam proident dolore. Ea est cupidatat proident consequat id laborum. Nostrud amet non labore eiusmod eiusmod adipisicing aliquip irure ipsum sint amet anim ut. Ut ex magna in adipisicing ea irure exercitation pariatur. Minim enim dolor id officia aliqua excepteur officia ullamco esse minim.\\r\\n\",\n" +
            "        \"image_url\": \"http://placehold.it/246x115\",\n" +
            "        \"image_source\": \"eu nulla commodo culpa\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 250,\n" +
            "        \"text_id\": \"2016/12/22/188\",\n" +
            "        \"date\": \"2015-10-08 03:36:21\",\n" +
            "        \"last_update\": \"2017-01-25 07:16:30\",\n" +
            "        \"version\": 25,\n" +
            "        \"title\": \"Ad irure cillum sint minim duis magna.\",\n" +
            "        \"rubric\": \"irure\",\n" +
            "        \"type\": \"sunt\",\n" +
            "        \"spb\": 1,\n" +
            "        \"json\": [\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Dolore adipisicing commodo culpa cillum officia consequat. Dolor eu voluptate occaecat nulla do ex ut aliquip fugiat exercitation laboris. Quis pariatur ex amet nostrud nisi pariatur non commodo est consequat exercitation. Adipisicing dolor quis aute in dolor mollit velit esse in qui dolor. Officia Lorem ex dolor et dolore nisi veniam elit.\\r\\nDeserunt nisi qui nisi commodo officia quis. Adipisicing incididunt sit do anim commodo aliqua minim ut nulla irure officia occaecat et proident. Laboris et excepteur ex amet laboris voluptate eiusmod tempor adipisicing consectetur non adipisicing.\\r\\nTempor pariatur commodo nisi enim. Sit sunt labore aute do laborum et exercitation culpa aute consequat cillum. Esse mollit velit labore enim deserunt. Sunt officia aute dolore esse exercitation voluptate non pariatur ad sit sunt sit magna aliquip. Proident esse nostrud proident eu do non aliqua enim voluptate qui enim voluptate.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"image\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://placehold.it/64x64\",\n" +
            "                        \"source\": \"dolor\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Irure aute eiusmod eiusmod aliquip do quis anim excepteur incididunt dolore aliquip occaecat est non. Ad veniam voluptate ad ea in adipisicing aliqua aliquip dolore dolore commodo veniam consectetur ex. Labore anim et qui minim ullamco tempor ipsum fugiat dolor do ut quis elit. Sit anim sint quis elit commodo elit dolor adipisicing elit consectetur aliquip. Aute eiusmod magna consequat laborum excepteur consequat proident incididunt commodo cillum Lorem ut. Irure culpa est enim laboris commodo nulla consequat dolor ea voluptate.\\r\\nEst nostrud adipisicing velit ea. Elit do dolore qui eiusmod est enim pariatur esse aliqua. Ex incididunt consequat eiusmod commodo proident ea.\\r\\nReprehenderit commodo non mollit cillum fugiat proident Lorem amet eiusmod qui Lorem excepteur. Ullamco velit consectetur cupidatat esse eu veniam non et laborum. Incididunt aliqua minim pariatur labore aute. Ad non magna quis nisi non velit ipsum incididunt quis aliquip magna aute laborum.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"video\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://www.example.com/do.mp4\",\n" +
            "                        \"source\": \"velit\",\n" +
            "                        \"poster\": \"http://placehold.it/140x100\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Est ipsum nisi aute anim et nostrud consectetur non amet eiusmod. Eu pariatur consequat ad id culpa excepteur ea sit officia ea excepteur aliquip velit minim. Eu exercitation esse reprehenderit velit duis proident. Pariatur labore non voluptate ad nisi nulla excepteur reprehenderit sint sit est esse laboris. Veniam ipsum elit reprehenderit sint non nisi ex fugiat laboris mollit elit sint.\\r\\nConsectetur ad non culpa mollit duis. Culpa ut ex ea qui. Est et velit sint sit enim minim exercitation culpa reprehenderit ex ea excepteur ullamco. Veniam duis adipisicing ullamco consequat enim esse non aliqua. Proident voluptate irure eiusmod nisi.\\r\\nIrure sunt minim dolor aute qui ad eu ex exercitation tempor aute commodo veniam fugiat. Est laboris Lorem tempor nisi sit in sunt duis labore elit consectetur. Qui nostrud eu est laborum excepteur ad. Est esse anim do consequat elit adipisicing Lorem ad sit consectetur voluptate reprehenderit deserunt proident.\\r\\n\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"link\": \"http://www.example.com/consectetur.htm\",\n" +
            "        \"forum_id\": 275192,\n" +
            "        \"lead\": \"Eiusmod nisi aute cupidatat incididunt in. Laborum ad deserunt anim nisi aliqua minim aute. Non consequat irure excepteur enim anim deserunt dolor pariatur aliquip reprehenderit. In ullamco sint incididunt mollit mollit eu do ad voluptate fugiat.\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 44,\n" +
            "        \"text_id\": \"2016/04/10/229\",\n" +
            "        \"date\": \"2016-02-02 03:00:17\",\n" +
            "        \"last_update\": \"2017-02-02 06:30:24\",\n" +
            "        \"version\": 26,\n" +
            "        \"title\": \"Ex reprehenderit qui ad proident cillum deserunt.\",\n" +
            "        \"rubric\": \"consequat\",\n" +
            "        \"type\": \"laborum\",\n" +
            "        \"spb\": 1,\n" +
            "        \"json\": [\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Voluptate culpa amet mollit duis in eu aliqua ullamco pariatur. Incididunt id non non labore qui deserunt exercitation culpa do reprehenderit esse. Ea ullamco id consequat officia sunt fugiat. Pariatur et nisi dolor et ut reprehenderit consequat duis consequat nulla reprehenderit voluptate. Tempor consectetur exercitation aliquip minim do sunt sunt. Deserunt esse incididunt ex elit esse amet id laborum ipsum aliqua aute. Id nulla tempor ad ullamco reprehenderit voluptate.\\r\\nEx elit nisi esse consectetur culpa officia do cillum culpa exercitation ut. Dolore sint ad ullamco sit. Ad Lorem id veniam est eu incididunt laboris cillum deserunt amet. Cupidatat tempor commodo cupidatat do deserunt nisi. Magna magna ullamco in magna tempor occaecat sunt incididunt eu veniam commodo veniam. Est consectetur deserunt ad ea irure. Ullamco ullamco et esse exercitation est nulla consequat aute velit esse est aute ullamco.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"image\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://placehold.it/64x64\",\n" +
            "                        \"source\": \"ipsum\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Cupidatat proident irure dolor aliquip minim. Culpa est aliquip culpa id ullamco amet ad excepteur ad cupidatat dolore ut eiusmod consectetur. Deserunt eu ut consectetur et pariatur consequat commodo laboris. Et occaecat officia nostrud eiusmod officia commodo culpa Lorem proident incididunt aliqua excepteur aliquip. Ut ad sit laborum consequat sint duis ullamco labore est dolore enim culpa.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"video\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://www.example.com/tempor.mp4\",\n" +
            "                        \"source\": \"adipisicing\",\n" +
            "                        \"poster\": \"http://placehold.it/140x100\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Officia labore consectetur officia velit do Lorem. Est occaecat eu eiusmod exercitation laboris do nisi ex proident minim consequat. Cillum occaecat proident deserunt tempor velit ipsum fugiat anim non sint.\\r\\nMagna veniam veniam pariatur velit culpa dolore tempor qui magna exercitation Lorem. Consectetur voluptate labore eiusmod ut velit in consequat sit laborum excepteur minim laboris consectetur excepteur. Pariatur sunt quis culpa cupidatat. Et Lorem qui id irure ex pariatur. Excepteur voluptate ad ut aliquip reprehenderit excepteur ad reprehenderit elit.\\r\\n\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"link\": \"http://www.example.com/deserunt.htm\",\n" +
            "        \"forum_id\": 140706,\n" +
            "        \"lead\": \"Id laborum aliqua velit aliqua sunt labore veniam laborum sit eiusmod officia fugiat nulla. Sunt enim non fugiat enim non veniam occaecat consectetur laborum ad. Culpa consequat labore tempor sit pariatur enim ipsum sunt.\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 154,\n" +
            "        \"text_id\": \"2016/10/07/244\",\n" +
            "        \"date\": \"2015-04-07 10:25:17\",\n" +
            "        \"last_update\": \"2016-06-28 05:44:13\",\n" +
            "        \"version\": 9,\n" +
            "        \"title\": \"Dolor laborum voluptate sint excepteur anim mollit anim deserunt exercitation dolor eu id consectetur.\",\n" +
            "        \"rubric\": \"laborum\",\n" +
            "        \"type\": \"minim\",\n" +
            "        \"spb\": 1,\n" +
            "        \"json\": [\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Nulla magna laboris mollit dolore. Minim ut culpa sit ex consectetur non. Nostrud reprehenderit sit in ad est. Lorem elit deserunt ea exercitation laborum ut tempor sint consequat consectetur veniam. Cupidatat sunt duis et qui nisi non. Est quis mollit exercitation reprehenderit exercitation sint adipisicing eu. Nulla pariatur et occaecat ea sunt non velit reprehenderit aute minim.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"image\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://placehold.it/64x64\",\n" +
            "                        \"source\": \"anim\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Et minim voluptate in eiusmod ad laborum nisi nostrud non tempor id et. Aute nisi ad proident aliqua aliquip tempor excepteur cupidatat non veniam excepteur. Labore sint enim ex deserunt nulla esse quis in ullamco esse nisi ipsum.\\r\\nCupidatat in nostrud aliquip tempor est minim aute est sunt. Voluptate id aliqua adipisicing esse fugiat irure cillum. Reprehenderit eu duis velit dolor sit nulla Lorem. Officia dolor ipsum aliqua ex.\\r\\n\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"video\",\n" +
            "                \"content\": [\n" +
            "                    {\n" +
            "                        \"url\": \"http://www.example.com/proident.mp4\",\n" +
            "                        \"source\": \"occaecat\",\n" +
            "                        \"poster\": \"http://placehold.it/140x100\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"text\",\n" +
            "                \"content\": \"Tempor commodo irure nostrud nulla eu in nulla ex exercitation incididunt mollit mollit. Esse nostrud ad est amet aute incididunt nisi aliquip quis. Officia tempor reprehenderit nisi velit. Sunt enim elit dolore anim ex.\\r\\nExcepteur et cupidatat eu excepteur quis labore nulla ea et. Eu est ullamco eu tempor magna consequat fugiat reprehenderit commodo qui consectetur commodo irure. Labore magna sit deserunt esse laboris aliquip nostrud ex est reprehenderit. Ipsum Lorem eiusmod aute incididunt. Tempor anim est consequat est non consequat excepteur ullamco consectetur qui esse Lorem quis ea. Non consectetur cillum minim laboris. Veniam non non cupidatat officia sunt minim commodo sit veniam.\\r\\n\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"link\": \"http://www.example.com/esse.htm\",\n" +
            "        \"forum_id\": 125511,\n" +
            "        \"lead\": \"Dolor dolore ipsum excepteur fugiat magna quis commodo ipsum reprehenderit in aliquip non duis exercitation. Nulla excepteur id do tempor dolor aliqua sunt ad dolore. Aliqua adipisicing adipisicing nostrud enim labore velit proident. Consectetur nostrud eiusmod dolor non aliqua enim excepteur deserunt. Ut esse aliqua pariatur eiusmod ipsum nulla occaecat fugiat in eiusmod consequat.\"\n" +
            "    }\n" +
            "]";
}