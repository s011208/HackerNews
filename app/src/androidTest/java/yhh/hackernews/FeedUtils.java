package yhh.hackernews;

import java.util.ArrayList;
import java.util.List;

import yhh.hackernews.feed.Comment;
import yhh.hackernews.feed.Story;

public class FeedUtils {
    private FeedUtils() {
    }

    public static List<Long> constructFakeTopStoryList() {
        List<Long> rtn = new ArrayList<>();
        rtn.add(15082810l);
        rtn.add(15083110l);
        rtn.add(15081349l);
        rtn.add(15082345l);
        rtn.add(15083231l);
        rtn.add(15080924l);
        rtn.add(15082415l);
        rtn.add(15083050l);
        rtn.add(15081382l);
        rtn.add(15083184l);
        rtn.add(15079619l);
        rtn.add(15082901l);
        rtn.add(15078094l);
        rtn.add(15078605l);
        rtn.add(15081410l);
        rtn.add(15081486l);
        rtn.add(15083186l);
        rtn.add(15078574l);
        rtn.add(15080174l);
        rtn.add(15081357l);
        rtn.add(15080985l);
        rtn.add(15078313l);
        rtn.add(15074991l);
        rtn.add(15081150l);
        rtn.add(15081733l);
        rtn.add(15073227l);
        rtn.add(15082116l);
        rtn.add(15081863l);
        rtn.add(15077858l);
        rtn.add(15064907l);
        rtn.add(15078112l);
        rtn.add(15077805l);
        return rtn;
    }

    public static List<Story> constructFakeStoryList() {
        List<Story> rtn = new ArrayList<>();
        rtn.add(new Story("{\"by\":\"ropiku\",\"descendants\":57,\"id\":15082810,\"kids\":[15083682,15083199,15083777,15083074,15083748,15083177,15083253,15083425,15083650,15083507,15083129,15083053,15083140,15083848,15083537,15083355,15083076,15083330,15083146,15083403,15083458,15083423],\"score\":132,\"time\":1503505512,\"title\":\"Google Cloud Platform: Introducing network service tiers\",\"type\":\"story\",\"url\":\"https://cloudplatform.googleblog.com/2017/08/introducing-Network-Service-Tiers-your-cloud-network-your-way.html\"}"));
        rtn.add(new Story("{\"by\":\"mxfh\",\"descendants\":21,\"id\":15083110,\"kids\":[15083773,15083517,15083398,15083500],\"score\":60,\"time\":1503507438,\"title\":\"New and improved bike routing, with low stress options\",\"type\":\"story\",\"url\":\"https://mapzen.com/blog/low-stress-bike-routing/\"}"));
        rtn.add(new Story("{\"by\":\"kragniz\",\"descendants\":80,\"id\":15081349,\"kids\":[15083669,15081611,15082064,15081987,15083297,15081970,15081809,15081574,15081942,15081613],\"score\":166,\"time\":1503495036,\"title\":\"Rethinking the D-Bus Message Bus\",\"type\":\"story\",\"url\":\"https://dvdhrm.github.io/rethinking-the-dbus-message-bus/\"}"));
        rtn.add(new Story("{\"by\":\"kartD\",\"descendants\":6,\"id\":15082345,\"kids\":[15082897,15083018],\"score\":58,\"time\":1503502683,\"title\":\"Hot Chips: Google TPU Performance Analysis\",\"type\":\"story\",\"url\":\"http://www.anandtech.com/show/11749/hot-chips-google-tpu-performance-analysis-live-blog-3pm-pt-10pm-utc\"}"));
        rtn.add(new Story("{\"by\":\"glhaynes\",\"descendants\":3,\"id\":15083231,\"kids\":[15083845,15083928,15083862],\"score\":21,\"time\":1503508159,\"title\":\"The Interface font family\",\"type\":\"story\",\"url\":\"https://rsms.me/interface/\"}"));
        rtn.add(new Story("{\"by\":\"mxbck\",\"descendants\":52,\"id\":15080924,\"kids\":[15081234,15081181,15081320,15081104,15081956,15081365,15081182,15081183,15081838,15082454,15081177,15081074,15081607],\"score\":176,\"time\":1503491491,\"title\":\"Offline-Friendly Forms in Progressive Web Apps\",\"type\":\"story\",\"url\":\"https://mxb.at/blog/offline-forms/\"}"));
        rtn.add(new Story("{\"by\":\"mcone\",\"descendants\":15,\"id\":15082415,\"kids\":[15083884,15083635,15083093,15082651,15083647,15083826,15083644,15082949,15082749],\"score\":54,\"time\":1503503088,\"title\":\"Beta Family: 50,000 Users to Test Your App\",\"type\":\"story\",\"url\":\"https://betafamily.com/\"}"));
        rtn.add(new Story("{\"by\":\"kvejdani\",\"descendants\":30,\"id\":15083050,\"kids\":[15083524,15083906,15083395,15083488,15083455,15083484,15083179,15083448,15083843,15083328,15083236,15083357,15083244,15083502,15083568],\"score\":28,\"text\":\"Hi HN, I&#x27;m Padideh Kamali-Zare, co-founder and CEO at Darmiyan in the current YC batch (<a href=\\\"https:&#x2F;&#x2F;www.darmiyan.com&#x2F;\\\" rel=\\\"nofollow\\\">https:&#x2F;&#x2F;www.darmiyan.com&#x2F;</a>). We work on early detection of Alzheimer&#x27;s disease.<p>I&#x27;m told that launching on HN should come with the backstory of how we came to work on this, so I need to tell you about my grandmother, the most precious gift in my life. She was a poet who raised me, and was always full of life and stories to keep me amazed and excited. As the first female bank executive in a conservative society in the middle east, she was also socially progressive and outstanding. A brilliant brain. A beautiful mind. A few months before she died, on a sunny day, she told me: “Do you know what I want the most from my life?” I stared at her in silence. She continued: “To die decently while I still remember myself, my memories and my loved ones. It feels like as I’m getting older, I’m somehow losing my brain. As if my brain was lemon juice before and now it’s becoming lemonade.” That statement has been stuck in my brain ever since. Her wish never came true. She died not remembering even basic things of her amazing life.<p>Now, 14 years later, it’s been exactly 14 years that I’m researching human brain structure and function, and modeling how they degrade with age and by diseases such as Alzheimer’s disease. Believe it or not, it has been 110 years since the initial description of this devastating disease by Dr. Alois Alzheimer. Yet there hasn’t been much progress in pre-symptomatic diagnosis of the disease and no progress in finding a cure for it despite all advances in science and technology. One in every five Medicare dollars is spent on Alzheimer’s disease and the entire health care system will go bankrupt if no revolution happens in the field. “So, what is missing?”, I always asked. And what can be done to find the missing piece? I always tried to answer. Driven by these questions, I spent several years in biological physics master’s and PhD programs and neuroscience postdoctoral research.<p>Now I’m the founding CEO of Darmiyan. At Darmiyan we detect Alzheimer’s disease up to 15 years before symptoms, meaning exactly when treatments are feasible and brain damage could be slowed down just by simple life style changes such as regular exercise, eating well, and sleeping well. We do this early detection non-invasively, using only standard brain MRI. We have spent the last three years in Darmiyan developing and validating a software platform that models the human brain and simulates the tissue architecture underlying every individual voxel (3D pixel) of the brain MRI. Our proprietary methodology and results have been officially reviewed and approved by clinical Alzheimer’s experts at Stanford and the world leading Alzheimer’s expert and Nobel prize winner Paul Greengard. The most challenging part of our journey so far has been to get access to the largest MRI databases for Alzheimer’s disease and clinically validate the software. Now we have analyzed more than 3000 brain scans and our software’s predictions are 90% accurate.<p>My co-founders are Thomas Liebmann, PhD, a top-notch experimental neuroscientist who has managed to visualize the most hidden parts of the human brain through the eyes of the most advanced microscopes; and Kaveh Vejdani, MD, an extraordinary physician who always seeks complex problems at the interface of physics, biology and medicine and solves them with high level of knowledge, creativity and innovation. Thomas was my first office-mate in Stockholm 12 years ago. We met when I had just started my PhD at the Royal Institute of Technology (KTH) in Sweden and we became friends on the first day. Kaveh and I met at a classical music event in New York 7 years ago and have been close friends ever since.<p>Our vision in Darmiyan is to help all those people in the world who suffer from complex brain diseases such as Alzheimer’s disease. We want them to be diagnosed early and get cured. We want to save those millions of precious brains who are unfairly stolen by Alzheimer’s disease and bring them back to their family members.<p>Thanks for reading to the end! We look forward to hearing your feedback and questions.\",\"time\":1503507029,\"title\":\"Launch HN: Darmiyan (YC S17) – Early Detection of Alzheimer's Disease\",\"type\":\"story\"}"));
        rtn.add(new Story("{\"by\":\"ilamont\",\"descendants\":62,\"id\":15081382,\"kids\":[15082191,15081820,15082041,15082119,15083532,15082975,15082163,15082401,15081773,15081981,15082124,15083885,15081969,15083098,15082508,15082362,15083561,15082698,15081761,15081781,15081946,15082394,15083101],\"score\":89,\"time\":1503495290,\"title\":\"Neo – A Word Processor for Authors\",\"type\":\"story\",\"url\":\"http://www.hughhowey.com/neo-a-word-processor-for-authors/\"}"));
        rtn.add(new Story("{\"by\":\"pmcpinto\",\"descendants\":3,\"id\":15083184,\"kids\":[15083912,15083888],\"score\":26,\"time\":1503507852,\"title\":\"How I Became Fake News\",\"type\":\"story\",\"url\":\"http://www.politico.com/magazine/story/2017/08/21/fake-news-charlottesville-215514\"}"));
        rtn.add(new Story("{\"by\":\"danmeade\",\"descendants\":121,\"id\":15079619,\"kids\":[15082656,15081706,15082304,15083704,15082115,15081509,15083801,15083712,15083853,15081787,15082463,15083621,15079807,15082795,15082156,15083433,15083642,15081172,15081341,15081139,15082794,15081601,15081129,15081483,15081868,15082327,15081206,15082622,15082465,15081855,15083114,15080994,15081719,15081802,15083872,15082818],\"score\":298,\"time\":1503476833,\"title\":\"Disconnect. Offline only\",\"type\":\"story\",\"url\":\"https://chris.bolin.co/offline/\"}"));
        rtn.add(new Story("{\"by\":\"igammarays\",\"descendants\":29,\"id\":15082901,\"kids\":[15083270,15083935,15083786,15083334,15083863,15083783,15083604,15083379,15083288,15083539,15083514,15083519,15083608,15083338,15083656,15083145,15083189,15083683,15083032,15083413,15083036],\"score\":48,\"text\":\"I&#x27;ve been working on a SaaS product side project for about 4 months now. Progress feels painfully slow, but I wonder what typical one-man consumer SaaS projects look like. Years of development? 6 months average? Specific examples would be helpful so that I can compare the scope&#x2F;size of the idea to mine.\",\"time\":1503506077,\"title\":\"Ask HN: How long did it take you to go from side project ideation to launch?\",\"type\":\"story\"}"));
        rtn.add(new Story("{\"by\":\"sohkamyung\",\"descendants\":63,\"id\":15078094,\"kids\":[15081077,15083790,15080933,15081494,15081014,15080589,15082724,15080938,15080605,15081228,15080969,15080986],\"score\":196,\"time\":1503455484,\"title\":\"A ship that flips 90 degrees for precise scientific measurements\",\"type\":\"story\",\"url\":\"http://www.atlasobscura.com/articles/flip-ocean-research-platform-scripps\"}"));
        rtn.add(new Story("{\"by\":\"kubopaper\",\"descendants\":104,\"id\":15078605,\"kids\":[15080523,15078671,15079958,15080189,15082436,15081238,15079772,15080715,15080683,15080006,15081447,15079741,15080400,15083193,15081011,15082252,15081274,15080201,15083415,15083223,15079348,15080723,15080289,15080483,15079290,15081405,15079818,15080491,15079943,15079527,15079605,15082365,15079113,15081825,15082460,15081429,15081108,15079546,15080730,15079558],\"score\":301,\"time\":1503463921,\"title\":\"HackerNews Grid\",\"type\":\"story\",\"url\":\"http://hackernewsgrid.com/\"}"));
        rtn.add(new Story("{\"by\":\"jmlr\",\"descendants\":18,\"id\":15081410,\"kids\":[15083931,15083752,15082427,15082922,15083678,15083674,15082885,15082813,15083163,15083252],\"score\":50,\"time\":1503495533,\"title\":\"Write a hash table in C\",\"type\":\"story\",\"url\":\"https://github.com/jamesroutley/write-a-hash-table\"}"));
        rtn.add(new Story("{\"by\":\"ingve\",\"descendants\":71,\"id\":15081486,\"kids\":[15082799,15083819,15083896,15082085,15083067,15083390,15082765,15083116,15083100,15082841,15082626],\"score\":112,\"time\":1503496126,\"title\":\"D as a Better C\",\"type\":\"story\",\"url\":\"http://dlang.org/blog/2017/08/23/d-as-a-better-c/\"}"));
        rtn.add(new Story("{\"by\":\"the_economist\",\"id\":15083186,\"score\":1,\"text\":\"BuildZoom’s data platform gathers data from a variety of sources, including thousands of regulatory entities, general contractors, their clients, and more. As the volume of interactions on the BuildZoom platform increases, the system becomes more intelligent and better able to guide consumers towards the right decision.<p>By making this research publicly available, we are able to dramatically increase the number of people that visit BuildZoom to research construction and remodeling. We currently receive about 60,000 US visitors&#x2F;day, and we hope to grow this exponentially by gathering and exposing extra data.<p>To accelerate the system’s rate of learning, we’re looking for an entrepreneur to systematize and scale existing processes and invent new ones that are aimed at gathering intelligence from the marketplace.<p>As an Entrepreneur in Residence, you will work directly with; our founders, a cross-disciplinary core management team, and Joe Lonsdale and Drew Oetting of 8VC; one of the top performing private funds currently managing ~$1.5 billion, to develop a strategic roadmap - complete with KPI’s and your own P&amp;L.<p>Specific objectives:<p>1. Develop and scale an automated process to use building permits, which signify that construction work has taken place, to elicit and codify feedback on the contractor who was hired to do the work; 2. Automatically identify industry-specific questions from consumers in the contracting purchase process, route them to the appropriate contracting professional in our network and implement a simple, elegant flow through which the professional can both answer the question and make a potential business contact; 3. Use an existing clustering methodology that infers which contracting professionals have worked with one another, to elicit and codify B2B feedback.<p>Requirements:<p>1. Entrepreneurial spirit - a  strong passion for gaining experience en route to eventually executing on your own vision; 2. Experience utilizing large scale data, harnessing it to build complex data systems and products; 3. Bachelor&#x27;s Degree in Computer Science, Engineering or related field; 4.  Full-stack experience, leading projects from concept to production and eventually scale, while staying mindful of business objectives and KPI’s; 5. Training and&#x2F;or great intuition when it comes to user-centered design; 6. Ability to leverage opportunities and maximize progress while navigating inherent obstacles and ambiguity; 7. Experience working on large, high-scale web applications; 8. Comfort working within an architectural framework that accommodates a complex data system; 9. Prior experience building data systems, products, and utilizing large scale data sets is highly preferred<p>Benefits:<p>1. Your work will have the potential to positively impact almost two million monthly users who are already engaging on the BuildZoom platform, as well as the 60M US homeowners in a $600B market; 2. Starting Day 1, you’ll have access to the industry’s most comprehensive store of data, which tracks the daily activities of over 2 million contracting professionals and over 40 million US properties; 3. You’ll derive first-hand experience working in collaboration with founders and investors to move an initiative forward; 4. Specifically, you’ll have unparalleled access to BuildZoom’s key investors, who will seed your own eventual concept upon successful completion of your tenure at BuildZoom; 5. Competitive salary and equity with bonuses for hitting key milestones and KPI’s<p>If this sounds like the opportunity you&#x27;ve been looking for:<p>Send a concise, personalized e-mail that explains why you are passionate about this opportunity to founders@buildzoom.com.\",\"time\":1503507892,\"title\":\"BuildZoom is hiring an Entrepreneur in Residence\",\"type\":\"job\"}"));
        rtn.add(new Story("{\"by\":\"darwhy\",\"descendants\":53,\"id\":15078574,\"kids\":[15081813,15079083,15082868,15079043,15083486,15079478,15079410,15079799,15078888,15079231,15082280,15078871,15079898,15080033],\"score\":246,\"time\":1503463444,\"title\":\"A history of branch prediction\",\"type\":\"story\",\"url\":\"https://danluu.com/branch-prediction/\"}"));
        rtn.add(new Story("{\"by\":\"reimertz\",\"descendants\":89,\"id\":15080174,\"kids\":[15080763,15080428,15080608,15080384,15081584,15080552,15081495,15080752,15080767,15080430,15083402,15082572,15081758,15081245,15083037,15081915,15081024,15081316,15083029,15080630],\"score\":144,\"time\":1503483963,\"title\":\"Stencil – A reusable web component generator\",\"type\":\"story\",\"url\":\"https://stenciljs.com/\"}"));
        rtn.add(new Story("{\"by\":\"rayuela\",\"descendants\":59,\"id\":15081357,\"kids\":[15082748,15083342,15082815,15083021,15082821,15083595,15082902,15082750,15083090,15082536,15083258,15082791,15082621],\"score\":56,\"time\":1503495114,\"title\":\"The Nuclear Tech Breakthrough That Could Make Oil Obsolete\",\"type\":\"story\",\"url\":\"https://www.bloomberg.com/news/articles/2017-08-22/the-nuclear-tech-breakthrough-that-could-make-oil-obsolete\"}"));
        rtn.add(new Story("{\"by\":\"mgrayson\",\"descendants\":1,\"id\":15080985,\"kids\":[15083865],\"score\":27,\"time\":1503491944,\"title\":\"Rendertron – a dockerized, headless Chrome rendering solution\",\"type\":\"story\",\"url\":\"https://github.com/GoogleChrome/rendertron\"}"));
        rtn.add(new Story("{\"by\":\"stenlix\",\"descendants\":69,\"id\":15078313,\"kids\":[15081117,15079286,15080268,15080134,15079953,15081958,15078643,15080000,15080813,15079030,15078795,15081420,15078828,15079249,15078810,15078608,15079185,15079462],\"score\":134,\"time\":1503459113,\"title\":\"Companies from Y Combinator’s Summer 2017 Demo Day (Day 2)\",\"type\":\"story\",\"url\":\"https://techcrunch.com/2017/08/22/yc-demo-day-s17-day-2/?utm_source=tcfbpage&sr_share=facebook\"}"));
        rtn.add(new Story("{\"by\":\"digital55\",\"descendants\":40,\"id\":15074991,\"kids\":[15080327,15079901,15079046,15078721,15081522,15080749,15080176,15080959,15078618,15080388,15079207,15079992],\"score\":126,\"time\":1503425498,\"title\":\"Mathematicians Tame Rogue Waves, Illuminating Future of LED Lighting\",\"type\":\"story\",\"url\":\"https://www.quantamagazine.org/mathematicians-tame-rogue-waves-illuminating-future-of-led-lighting-20170822/\"}"));
        rtn.add(new Story("{\"by\":\"champagnepapi\",\"descendants\":272,\"id\":15081150,\"kids\":[15081637,15081682,15082982,15081581,15081720,15082755,15081860,15082214,15081927,15081768,15083359,15081728,15082025,15081558,15081527,15081835,15081776,15081513,15083558,15081727,15081935,15082348,15082205,15081484,15081680,15082882,15083440,15082888,15082068,15081778,15082820,15082619,15081872],\"score\":239,\"time\":1503493331,\"title\":\"Wall Street Banks Warn Downturn Is Coming\",\"type\":\"story\",\"url\":\"https://www.bloomberg.com/news/articles/2017-08-22/wall-street-banks-warn-winter-is-coming-as-business-cycle-peaks\"}"));
        rtn.add(new Story("{\"by\":\"kaplun\",\"descendants\":4,\"id\":15081733,\"kids\":[15083590,15083530],\"score\":14,\"time\":1503498169,\"title\":\"ScienceBeam – using computer vision to extract PDF data\",\"type\":\"story\",\"url\":\"https://elifesciences.org/labs/5b56aff6/sciencebeam-using-computer-vision-to-extract-pdf-data\"}"));
        rtn.add(new Story("{\"by\":\"tellarin\",\"descendants\":41,\"id\":15073227,\"kids\":[15081246,15081173,15080013,15080691,15080103,15079331,15083374,15080976,15080221,15079661,15081536,15082745,15079140,15081923,15079541,15079212,15079438,15079635,15080422,15079675],\"score\":114,\"time\":1503415099,\"title\":\"The Enduring Legacy of Zork\",\"type\":\"story\",\"url\":\"https://www.technologyreview.com/s/608670/the-enduring-legacy-of-zork/\"}"));
        rtn.add(new Story("{\"by\":\"phosphate\",\"descendants\":21,\"id\":15082116,\"kids\":[15083741,15083899,15083697,15083416,15083430,15083435,15083804],\"score\":42,\"text\":\"Several employees vouching the info of the record, no public links available yet.\",\"time\":1503501294,\"title\":\"ThoughtWorks has been sold to private equity\",\"type\":\"story\"}"));
        rtn.add(new Story("{\"by\":\"ingve\",\"descendants\":56,\"id\":15081863,\"kids\":[15082076,15083839,15082152,15083206,15082291,15082117,15083302,15082343,15082947,15082673],\"score\":88,\"time\":1503499385,\"title\":\"Code Smells: Iteration\",\"type\":\"story\",\"url\":\"https://blog.jetbrains.com/idea/2017/08/code-smells-iteration/\"}"));
        rtn.add(new Story("{\"by\":\"anythingnonidin\",\"descendants\":26,\"id\":15077858,\"kids\":[15081799,15082200,15082961,15083663],\"score\":76,\"time\":1503452371,\"title\":\"Psilocybin-assisted group therapy for demoralization in long-term AIDS survivors\",\"type\":\"story\",\"url\":\"http://clinicaltrials.ucsf.edu/trial/NCT02950467\"}"));
        rtn.add(new Story("{\"by\":\"artsandsci\",\"descendants\":24,\"id\":15064907,\"kids\":[15083701,15081340,15083317,15080394,15082065,15065018,15082066,15079793,15080347,15080984,15081685],\"score\":56,\"time\":1503326252,\"title\":\"Why George Guidall Is the Undisputed King of Audiobooks\",\"type\":\"story\",\"url\":\"https://www.nytimes.com/2017/08/17/books/george-guidall-audiobooks.html\"}"));
        rtn.add(new Story("{\"by\":\"happy-go-lucky\",\"descendants\":38,\"id\":15078112,\"kids\":[15083513,15081943,15081120,15082145,15080165,15082509,15080387,15080739,15081138,15080380],\"score\":68,\"time\":1503455720,\"title\":\"The Dark Side of Resilience\",\"type\":\"story\",\"url\":\"https://hbr.org/2017/08/the-dark-side-of-resilience\"}"));
        rtn.add(new Story("{\"by\":\"sohkamyung\",\"descendants\":75,\"id\":15077805,\"kids\":[15082642,15082293,15082377,15082017,15081562,15080935,15079061],\"score\":100,\"time\":1503451580,\"title\":\"Sun Flyer, an Electric Trainer Aircraft\",\"type\":\"story\",\"url\":\"http://spectrum.ieee.org/aerospace/aviation/cheaper-lighter-quieter-the-electrification-of-flight-is-at-hand\"}"));
        return rtn;
    }

    public static List<Comment> constructFakeCommentList() {
        List<Comment> rtn = new ArrayList<>();
        rtn.add(new Comment("{\"by\":\"Veratyr\",\"id\":15083682,\"kids\":[15084095,15083937,15083902,15083976],\"parent\":15082810,\"text\":\"A long standing complaint of mine is that Cloud egress pricing severely limits the usefulness of compute. If I want to say process some visual effects on a large (1TB) ProRes video, I might spend $1 on the compute but $100 on the egress getting it back.<p>Unfortunately these changes don&#x27;t really resolve that problem. &quot;Standard&quot; pricing is a paltry 20% less. That 1TB video egress still costs $80 and for that price I can rent a beefy server with a dedicated gigabit pipe for a month.<p>Why is &quot;Cloud&quot; bandwidth so damned expensive?<p>I&#x27;d love a &quot;best effort&quot; or &quot;off peak&quot; tier. I imagine Google&#x27;s pipes are pretty empty when NA is asleep and my batch jobs aren&#x27;t really going to care.\",\"time\":1503511371,\"type\":\"comment\"}"));
        rtn.add(new Comment("{\"by\":\"rb808\",\"id\":15084095,\"parent\":15083682,\"text\":\"I was going to back up my photos to AWS Glacier before noticing that retrieval costs are multiple times the storage cost. I guess that is possibly OK for a backup but scared me into a physical alternative.<p><a href=\\\"http:&#x2F;&#x2F;liangzan.net&#x2F;aws-glacier-calculator&#x2F;\\\" rel=\\\"nofollow\\\">http:&#x2F;&#x2F;liangzan.net&#x2F;aws-glacier-calculator&#x2F;</a>\",\"time\":1503514364,\"type\":\"comment\"}"));
        rtn.add(new Comment("{\"by\":\"stormbeard\",\"id\":15083937,\"parent\":15083682,\"text\":\"It&#x27;s expensive to pull data out of their cloud partly because it makes switching providers or moving on-premise less appealing. It&#x27;s a trap.\",\"time\":1503513325,\"type\":\"comment\"}"));
        rtn.add(new Comment("{\"by\":\"deafcalculus\",\"id\":15083902,\"kids\":[15083966],\"parent\":15083682,\"text\":\"The high bandwidth price is mostly to drive away seedboxes and porn sites. Huge discounts are likely available to big customers.\",\"time\":1503513105,\"type\":\"comment\"}"));
        rtn.add(new Comment("{\"by\":\"nik736\",\"id\":15083966,\"parent\":15083902,\"text\":\"They could simply ban adult websites in their ToS, I doubt this is the reason. Also seed boxes are nowhere near useful if you still would pay $10 &#x2F; TB and at the same time can get traffic for free at OVH or Online&#x2F;Scaleway.<p>It&#x27;s most likely vendor lock-in and people are dumb enough to pay for it.\",\"time\":1503513602,\"type\":\"comment\"}"));
        rtn.add(new Comment("{\"by\":\"pbbakkum\",\"id\":15083748,\"kids\":[15084060,15083996],\"parent\":15082810,\"text\":\"A few notes here:<p>- An unmentioned alternative to this pricing is that GCP has a deal with Cloudflare that gives you a 50% discount to what is now called Premium pricing for traffic that egresses GCP through Cloudflare. This is cheaper for Google because GCP and Cloudflare have a peering arrangement. Of course, you also have to pay Cloudflare for bandwidth.<p>- This announcement is actually a small price cut compared to existing network egress prices for the 1-10 TiB&#x2F;month and 150+ TiB&#x2F;month buckets.<p>- The biggest advantage of using private networks is often client latency, since packets avoid points of congestion on the open internet. They don&#x27;t really highlight this, instead showing a chart of throughput to a single client, which only matters for a subset of GCP customers. The throughput chart is also a little bit deceptive because of the y-axis they&#x27;ve chosen.<p>- Other important things to consider if you&#x27;re optimizing a website for latency are CDN and where SSL negotiation takes place. For a single small HTTPS request doing SSL negotiation on the network edge can make a pretty big latency difference.<p>- Interesting number: Google capex (excluding other Alphabet capex) in both 2015 and 2016 was around $10B, at least part of that going to the networking tech discussed in the post. I expect they&#x27;re continuing to invest in this space.<p>- A common trend with GCP products is moving away from flat-rate pricing models to models which incentivize users in ways that reflect underlying costs. For example, BigQuery users are priced per-query, which is uncommon for analytical databases. It&#x27;s possible that network pricing could reflect that in the future. For example, there is probably more slack network capacity at 3am than 8am.\",\"time\":1503512005,\"type\":\"comment\"}"));
        rtn.add(new Comment("{\"by\":\"vgt\",\"id\":15084060,\"parent\":15083748,\"text\":\"I like your thinking, but one minor clarification. BigQuery&#x27;s actually introduced Flat Rate [0] (introducing it a year ago) and Committed Use Discounts [1] (Amazon RIs are similar) since that&#x27;s kind of what some Enterprises want. These are optional and flexible.<p>I personally still hold that pay-per-use pricing is the cloud native approach [2], the most cost-efficient, and the most customer-friendly. However, it&#x27;s unfamiliar and hard to predict, so starting out on Flat Rate pricing as a first step makes sense.<p>( work at Google and was a part of the team that introduced BQ Flat Rate)<p>[0] <a href=\\\"https:&#x2F;&#x2F;cloud.google.com&#x2F;bigquery&#x2F;pricing#flat_rate_pricing\\\" rel=\\\"nofollow\\\">https:&#x2F;&#x2F;cloud.google.com&#x2F;bigquery&#x2F;pricing#flat_rate_pricing</a><p>[1] <a href=\\\"https:&#x2F;&#x2F;hackernoon.com&#x2F;why-googles-answer-to-aws-reseved-instances-is-a-big-deal-4b9b36d8e631\\\" rel=\\\"nofollow\\\">https:&#x2F;&#x2F;hackernoon.com&#x2F;why-googles-answer-to-aws-reseved-ins...</a><p>[2] <a href=\\\"https:&#x2F;&#x2F;cloud.google.com&#x2F;blog&#x2F;big-data&#x2F;2016&#x2F;02&#x2F;visualizing-the-mechanics-of-on-demand-pricing-in-big-data-technologies\\\" rel=\\\"nofollow\\\">https:&#x2F;&#x2F;cloud.google.com&#x2F;blog&#x2F;big-data&#x2F;2016&#x2F;02&#x2F;visualizing-t...</a>\",\"time\":1503514148,\"type\":\"comment\"}"));
        rtn.add(new Comment("{\"by\":\"chrisabrams\",\"id\":15083996,\"parent\":15083748,\"text\":\"Pretty much every major CDN and Google&#x2F;AWS have a peering agreement that gives discount egress.\",\"time\":1503513789,\"type\":\"comment\"}"));
        rtn.add(new Comment("{\"by\":\"jstapels\",\"id\":15083199,\"kids\":[15083303,15083774,15083242],\"parent\":15082810,\"text\":\"Egress pricing for Google and AWS (sans Lightsail) continues to be one of the biggest price differences between them and smaller hosts such as Linode and DigitalOcean.<p>I think Google missed an opportunity here. They should have cut the prices more significantly for standard tier (sacrificing performance) to make this more competitive.<p>Right now Linode&#x27;s and DO&#x27;s smallest $5 plan offers 1TB of transfer, which would cost $85.00 on Google&#x27;s new standard plan.\",\"time\":1503507934,\"type\":\"comment\"}"));
        rtn.add(new Comment("{\"by\":\"fabian2k\",\"id\":15083303,\"kids\":[15084145],\"parent\":15083199,\"text\":\"The extremely high egress prices for any cloud don&#x27;t seem to have hurt their popularity much so far. So I suspect they all don&#x27;t want to give up their cash cow.<p>Compared to a VPS or renting a dedicated servers, the egress costs can be enormous if you come even close to using the traffic contingent you get with many VPS or dedicated hosts.<p>Just as a comparison, a dedicated server with Hetzner for ~50-70 EUR per month includes 30 TB of traffic, which would be at least 2,400 EUR on the Google Cloud.\",\"time\":1503508704,\"type\":\"comment\"}"));
        rtn.add(new Comment("{\"by\":\"notyourday\",\"id\":15084145,\"parent\":15083303,\"text\":\"Just put the edges somewhere else, use GCP for compute and push the actual delivery to the edge.\",\"time\":1503514764,\"type\":\"comment\"}"));
        rtn.add(new Comment("{\"by\":\"idorosen\",\"id\":15083074,\"kids\":[15083471,15083209],\"parent\":15082810,\"text\":\"TL;DR: New Standard tier level is hot potato routing while existing (now called Premium) tier is cold potato routing.<p><a href=\\\"https:&#x2F;&#x2F;en.wikipedia.org&#x2F;wiki&#x2F;Hot-potato_and_cold-potato_routing\\\" rel=\\\"nofollow\\\">https:&#x2F;&#x2F;en.wikipedia.org&#x2F;wiki&#x2F;Hot-potato_and_cold-potato_rou...</a>\",\"time\":1503507184,\"type\":\"comment\"}"));
        rtn.add(new Comment("{\"by\":\"panarky\",\"id\":15083471,\"parent\":15083074,\"text\":\"Other cloud providers like AWS and Azure use hot-potato routing which has lower performance and reliability than GCP&#x27;s current network offering.<p>So Google is offering a new &quot;standard tier&quot; equivalent to AWS and Azure, and undercutting both of them on network egress costs by a small amount.<p>Network egress costs are still astronomical.\",\"time\":1503509939,\"type\":\"comment\"}"));
        return rtn;
    }
}
