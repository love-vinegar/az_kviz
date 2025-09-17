package cz.personal.vinegar.services;

import cz.personal.vinegar.dataObjects.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.LinkedList;

import java.util.List;
import java.util.Queue;

@Service
@Slf4j
public class QuestionService {

    Queue<Question> questionsFirstPlayer;
    Queue<Question> questionsSecondPlayer;
    Queue<Question> questionsYesNo;
    public QuestionService () {
        fillQuestions();
    }

    public void fillQuestions() {
        questionsFirstPlayer = new LinkedList<>();
        questionsFirstPlayer.addAll(List.of(
                new Question("Jaké je Bára znamení", "P", "Panna", true),
                new Question("Jak se jemnovala bářina mamka za svobodna", "P", "Pokorná", true),
                new Question("Jaké je celé jméno nevěsty", "BP", "Barbora Podlisková", true),
                new Question("Bára se umístila druhá v pražském kole dějepisné olimpiády, porazil ji jeden ze svatebčanů. Kdo to je?", "TK", "Tomáš Karásek", true),
                new Question("Jak se jemnovalo Bářino morče", "B", "Belinka", true),
                new Question("Jak se jmenovala Bára, když si v dětství hrála na psa", "B", "Brita", true),
                new Question("Jak se jmenuje hospoda, kde Bára pořádá srazy se svojí třídou?", "PP", "Plný pekáč", true),
                new Question("Co je Báry oblíbený drink?", "GT", "Gin Tonic", true),
                new Question("Jak se jemnuje skupina se kterou  Bára tancuje irské tance", "GT", "Gall Tír", true),
                new Question("Který z hostů chodil s Bárou už na první stupeň ZŠ", "EJ", "Eliška Johanisová", true),
                new Question("Jaký stát navštívila Bára nejvíckrát za život", "R", "Rakousko", true),
                new Question("Na dva jaký hudební nástroje se Bára snažila (marně) naučit (stačí jeden)", "KF", "Kytara, fletna", true),
                new Question("Po jakém jídle se Bára v dětství pozvracela a od té doby ho nejí", "R", "Rajská", true),
                new Question("Jakou knihu si Bára vytáhla u maturity?", "H", "Hamlet", true)
        ));

        questionsSecondPlayer = new LinkedList<>();
        questionsSecondPlayer.addAll(List.of(
                new Question("Jaké předměty Vojta v dospívání sbíral?", "P", "Plechovky", false),
                new Question("Jak se jmenuje sci-fi seriál, který Vojta sledoval jako mladší?", "HB", "Hvězdná Brána", false),
                new Question("Čím chtěl být Vojta jako malý?", "PA", "Paleontologem nebo astronautem", false),
                new Question("Na stadionu kterého týmu bylo Vojta naposledy na hokeji?", "SP", "Sparta Praha (chyták)", false),
                new Question("Jaké pochutiny si Vojta nejčastěji objednává online?", "M", "Mixit", true),
                new Question("Jak se jmenoval křestním jménem Vojtův nejlepší kamarád ze základní školy?", "J", "Jenda? (Jan)", true),
                new Question("Jakou online hru Vojta nejvíc hrál se spolužáky s gymnázia?", "SaF", "Shakes and Fidget", true),
                new Question("Jaký podnik rádi navštěvovali Vojta a jeho spolužáci na gymnáziu (resp. v jeho druhé půlce)?", "NV", "Na Verandě", true),
                new Question("Při jaké aktivitě Vojta ztratil svoje klíče na konci základní školy?", "HK", "Házení klíčů (přes keř) s Kardou", true),
                new Question("Jakou přezdívku měl Vojta jako mladší? (používal ji i jako jméno v online hrách)", "V", "Vojtěš", true),
                new Question("Jaký je vojtův nejoblíbenější tradiční český film?", "P", "Pelíšky", false),
                new Question("Jak se jmenuje základní škola na kterou Vojta chodil?", "ZŠnS", "ZŠ Na Slovance", false),
                new Question("Jaké zvíře měl Vojta jako malý?", "RK", "Rybičky a křečka", false),
                new Question("Jak se jmenoval křeček, kterého vlastnil Vojta s jeho bráchou?", "Ď", "Ďáblík", false),
                new Question("Jaká byla Vojtova první brigáda?", "ČAV", "Čištení archeologických vykopávek", false),
                new Question("Jak se měl Vojta jmenovat, kdyby byl holkou? ", "A", "Anička (Anna)", false),
                new Question("Kam Vojta poprvé letěl letadlem? ", "KO", "Kanárské ostrovy", false),
                new Question("Proč měl Vojta odklad?", "PssnMRH", "Protože si s ním maminka ráda hrála doma", false),
                new Question("Kam se Vojta hlásil na VŠ mimo práv (Alespoň dva)?", "H P MV", "Historie, politologie a mez. vztahy", false)
        ));

        questionsYesNo = new LinkedList<>();
        questionsYesNo.addAll(List.of(
                new Question("Nejoblíbenější měsíc pro svatby je červen", "X", "Ano, naopak v lednu je svateb nejméně"),
                new Question("Je pravda, že v Japonsku je tradiční svatební oděv nevěsty čistě červený?", "X", "Ne, je to tradiční bílé kimono"),
                new Question("Průměrný věk nevěst na svatbě je 30 let ", "X", "Ano, průměrný věk ženichů je 32 let"),
                new Question("Může Česku být jako svatební svědek i cizinec bez českého občanství?", "X", "Ano, svědek může být i cizinec, musí být jen plnoletý a prokázat totožnost."),
                new Question("Průměrný věkový rozdíl mezi snoubenci v ČR jsou pouhé 2 roky", "X", "Ne, je to 5 a půl"),
                new Question("Od začátku československa bylo nejvíce uzavřených svateb v roce 1946", "X", "Ne, bylo to v roce 1920. V daný rok se uzavřelo 135 714"),
                new Question("Kdyby Vojta chtěl Báru vzít v roce 1985 na rande kde propijou celý jeho průměrný plat za tuzemský rum, mohl by pozvat Báru na 30 lahví tuzemáku", "X", "Ano, je tomu tak. Také by jí mohl koupit 200 tabulek čokolády"),
                new Question("Snubní prsten se nosí na pravém prsteníčku, protože si dříve lidé mysleli, že k němu vede žíla přímo ze srdce ", "X", "Ne, snubní prsten se nosí na levém prsteníčku ")
        ));
    }

    public Question getQuestion(boolean firstPlayer, int questionIndex) {
        if(firstPlayer) {
            return questionsFirstPlayer.poll();
        } else {
            return questionsSecondPlayer.poll();
        }
    }

    public Question getYesNoQuestion(int questionIndex) {
        return questionsYesNo.poll();
    }
}
