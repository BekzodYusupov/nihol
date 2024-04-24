import SwiftUI
import shared
import CodeScanner

struct ContentView: View {
    @State private var isShowingScanner: Bool = false
    @State var scan1: String = "not updated"
    @State var scan2: String = "not updated"
    @ObservedObject private var viewModel: MainViewModel = GetViewModels().getMainViewModel()

	var body: some View {
        VStack {
            Button(action: {
                isShowingScanner = true
            }, label: {
                Text("scan")
            })
            
            Text(scan1)
            Text(scan2)
            
            Spacer(minLength: 100)
            Text("\(viewModel.viewState.counter)")
            Button(action: {
                viewModel.incCount()
            }, label: {
                Text("+1")
            })
            
            Button(action: {
                viewModel.reduceCount()
            }, label: {
                Text("-1")
            })
            
            Text("response: \(viewModel.viewState.qr ?? "nil")")
            
            
        }
        .sheet(isPresented: $isShowingScanner){
            CodeScannerView(codeTypes: [.qr], simulatedData: "hhtps" ,completion: handleScan)
        }
    }
        
    func handleScan(result: Result<ScanResult, ScanError>){
        isShowingScanner = false
        
        switch result {
        case .success(let result):
            print("success :\(result.string)")
            scan1 = result.string
        case .failure(let error):
            print("errorrrrrr :\(error.localizedDescription)")
        }
    
    }
}

private extension MainViewModel  {
    var viewState: MainViewModelState {
        get {
            return self.state(\.uiState, equals: { $0 === $1 }, mapper: { $0 })
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
